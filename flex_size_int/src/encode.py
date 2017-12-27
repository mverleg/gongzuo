
from math import log, floor, ceil
from struct import Struct
from utils import DATAPERBYTE, NEGATIVE, LASTBYTE, BitShift

tobit = Struct('!b')


def long_to_flex(nr, shift=None):
	if shift is None:
		shift = BitShift(0, [0])
	assert isinstance(shift, BitShift)
	# Create the flex-integer bytes
	bytes = _convert_ulong_to_flex_with_offset(abs(int(nr)), shift.amt + 1, left_padding=1)
	# Set the second bit of the first byte to 1 if negative
	if nr < 0:
		bytes[0] += NEGATIVE
	# Add the data from the shift to the bytes
	bytes = _combine_shiftdata_and_flexint(shift.data, bytes)
	return bytes


def ulong_to_flex(nr, shift=None):
	if shift is None:
		shift = BitShift(0, [0])
	assert isinstance(shift, BitShift)
	assert nr >= 0, "this function is for unsigned numbers"
	# Create the flex-integer bytes
	bytes = _convert_ulong_to_flex_with_offset(nr, shift.amt)
	# Add the data from the shift to the bytes
	bytes = _combine_shiftdata_and_flexint(shift.data, bytes)
	## for bytenr, shiftdata in enumerate(shift.data):
	## 	bytes[bytenr] = shiftdata if bytes[bytenr] is None else bytes[bytenr] + shiftdata
	return bytes


def _convert_ulong_to_flex_with_offset(nr, offset, left_padding=0):
	"""
	Code shared between `long_to_flex` and `ulong_to_flex`.
	
	:param nr: The positive number to convert to flex bytes format.
	:param offset: The offset, including shift-data and negative sign, but without continuations.
	:param left_padding: How many spaces to leave between the continuation mark and the data (e.g. for the sign).
	"""
	if (nr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	assert nr >= 0
	# Find how many blocks are needed for the shift, data and bookkeeping
	bytecount = _determine_byte_count(nr, offset)
	bytes = [None] * bytecount
	# All except first flexnr blocks have 7 bits
	first_block_index = offset // 8
	for blocknr in range(bytecount - 1, first_block_index, -1):
		bytes[blocknr] = tobit.pack(nr % DATAPERBYTE)[0]
		nr >>= 7
	# First flexnr block has 6 or fewer bits
	first_block_nondata = (offset % 8) + 1
	assert nr < 2**(8 - first_block_nondata), "remaining data is too large for first byte's size (this is a bug)"
	# Shift the data to occupy the
	bytes[0] = tobit.pack(nr)[0] << (first_block_nondata - 1 - left_padding)
	# Set the first bit of last byte to 1
	bytes[-1] = LASTBYTE if bytes[-1] is None else bytes[-1] + LASTBYTE
	return bytes


def _combine_shiftdata_and_flexint(shift_data_bytes, flex_int_bytes):
	"""
	Combine the shift data and the flex int data into a single list of bytes.
	
	:param shift_data: Data to move, with length equal to the minimum bytes for the data, with the last byte right-aligned.
	:param flex_int_bytes: Data representing the flex int, with empty values already padded to the left for the shift data. Changed in-place.
	"""
	for bytenr, shiftdata in enumerate(shift_data_bytes):
		flex_int_bytes[bytenr] = shiftdata if flex_int_bytes[bytenr] is None else flex_int_bytes[bytenr] + shiftdata
	return flex_int_bytes


def _determine_byte_count(numerical_value, fixed_data_bits):
	"""
	Determine how many bytes exactly are needed to store a number along with some fixed/bookkeeping data,
	using 7 data bits and one continuation bit per byte for the numerical value, and 8 bits per byte for other data.
	
	:param fixed_data_bits: This includes the sign, and any shift data (but not continuation bits).
	:param numerical_value: The number to be stored in flexible size format.
	"""
	if (numerical_value == 0):
		number_bits = 1
	else:
		# Use this instead of logarithm because logarithm has rounding errors for large numbers.
		number_bits = abs(numerical_value).bit_length()
	continuation_bits = int(floor(number_bits / 7.))
	return int(ceil((number_bits + continuation_bits + fixed_data_bits) / 8.))


