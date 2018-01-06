
from encode import long_to_flex, ulong_to_flex
from utils import LASTBYTE, POSITIVE, NEGATIVE, DATAPERBYTE, BitShift


def test_long_to_flex():
	assert [POSITIVE + LASTBYTE] == long_to_flex(0) 
	assert [POSITIVE + LASTBYTE + 25] == long_to_flex(25) 
	assert [POSITIVE + LASTBYTE + 58] == long_to_flex(58) 
	assert [POSITIVE + 0, LASTBYTE + 115] == long_to_flex(115) 
	assert [NEGATIVE, LASTBYTE + 115] == long_to_flex(-115) 
	assert [NEGATIVE + 25, 27, LASTBYTE + 121] == long_to_flex(-413177) 
	assert [0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127] == long_to_flex(9223372036854775807) 
	""" Cover edge cases """
	for (signbit, sign) in ((POSITIVE, +1), (NEGATIVE, -1)):
		assert [signbit + LASTBYTE + 63] == long_to_flex(sign * 63) 
		assert [signbit + 0, LASTBYTE + 64] == long_to_flex(sign * 64) 
		assert [signbit + 0, LASTBYTE + 127] == long_to_flex(sign * 127) 
		assert [signbit + 1, LASTBYTE] == long_to_flex(sign * 128) 
		assert [signbit + 1, LASTBYTE + 1] == long_to_flex(sign * 129) 
		assert [signbit + 1, LASTBYTE + DATAPERBYTE - 1] == long_to_flex(sign * 255) 
		assert [signbit + 2, LASTBYTE] == long_to_flex(sign * 256) 
		assert [signbit + 4, LASTBYTE] == long_to_flex(sign * 512) 
		assert [signbit + 63, LASTBYTE + DATAPERBYTE - 1] == long_to_flex(sign * 8191) 
		assert [signbit, 8192 // (2 ** 7), LASTBYTE] == long_to_flex(sign * 8192) 
		assert [signbit, DATAPERBYTE - 1, LASTBYTE + DATAPERBYTE - 1] == long_to_flex(sign * 16383) 
		assert [signbit + 1, 0, LASTBYTE] == long_to_flex(sign * 16384)


def test_ulong_to_flex():
	assert ulong_to_flex(0) == [LASTBYTE]
	assert ulong_to_flex(63) == [63 + LASTBYTE]
	assert ulong_to_flex(64) == [64 + LASTBYTE]
	assert ulong_to_flex(127) == [127 + LASTBYTE]
	assert ulong_to_flex(128) == [1, LASTBYTE]
	assert ulong_to_flex(16383) == [DATAPERBYTE - 1, DATAPERBYTE - 1 + LASTBYTE]
	assert ulong_to_flex(16384) == [1, 0, LASTBYTE]
	assert ulong_to_flex(2**24) == [8, 0, 0, LASTBYTE]
	assert ulong_to_flex(2**49) == [1, 0, 0, 0, 0, 0, 0, LASTBYTE]


def test_long_to_flex_shift():
	#TODO
	pass


def test_ulong_to_flex_shift():
	assert ulong_to_flex(12, shift=BitShift(0, [0])) == [12 + LASTBYTE]
	assert ulong_to_flex(12, shift=BitShift(1, [1])) == [24 + 1 + LASTBYTE]
	assert ulong_to_flex(0, shift=BitShift(3, [7])) == [7 + LASTBYTE]
	assert ulong_to_flex(0, shift=BitShift(19, [1, 0, 7])) == [1, 0, 7 + LASTBYTE]
	#TODO: more!
	# assert ulong_to_flex(63) == [63 + LASTBYTE]
	# assert ulong_to_flex(64) == [64 + LASTBYTE]
	# assert ulong_to_flex(127) == [127 + LASTBYTE]
	# assert ulong_to_flex(16383) == [DATAPERBYTE - 1, DATAPERBYTE - 1 + LASTBYTE]
	# assert ulong_to_flex(16384) == [1, 0, LASTBYTE]
	# assert ulong_to_flex(2**24) == [8, 0, 0, LASTBYTE]
	# assert ulong_to_flex(2**49) == [1, 0, 0, 0, 0, 0, 0, LASTBYTE]


