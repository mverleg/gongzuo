
from utils import NEGATIVE, DATAPERBYTE


def flex_to_long(bytes):
	if bytes[0] & NEGATIVE:
		parity = -1
		nr = bytes[0] - NEGATIVE
	else:
		parity = 1
		print('positive: {}'.format(bytes))
		nr = bytes[0]
	for byte in bytes[1:]:
		nr *= DATAPERBYTE
		nr += byte
	nr -= DATAPERBYTE
	return parity * nr

