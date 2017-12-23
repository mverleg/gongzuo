from utils import NEGATIVE, DATAPERBYTE, LASTBYTE, InvalidFlexInt


def flex_to_long(bytes):
	if len(bytes) == 1 and bytes[0] == LASTBYTE + NEGATIVE:
		raise InvalidFlexInt("Invalid byte 01000000 found (-0 is not a valid flex int)")
	# Check if the number is negative
	if bytes[0] & NEGATIVE:
		parity = -1
		nr = bytes[0] - NEGATIVE
	else:
		parity = 1
		nr = bytes[0]
	# Read bytes from left to right, keep multiplying to reach the correct significance
	for byte in bytes[1:]:
		nr *= DATAPERBYTE
		nr += byte
	# Remove the terminator bit
	nr -= DATAPERBYTE
	return parity * nr


def flex_to_ulong(bytes):
	parity = 1
	nr = bytes[0]
	# Read bytes from left to right, keep multiplying to reach the correct significance
	for byte in bytes[1:]:
		nr *= DATAPERBYTE
		nr += byte
	# Remove the terminator bit
	nr -= DATAPERBYTE
	return parity * nr


