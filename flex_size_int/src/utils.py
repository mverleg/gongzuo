
from math import log, ceil


DATAPERBYTE = LASTBYTE = 1 << 7
NEGATIVE = 1 << 6


class InvalidFlexInt(Exception):
	""" The value 01000000 is not valid flex format (it would have been -0). """
	

class InvalidShiftSize(Exception):
	""" The shift size should be positive and long enough to support the data. """


class BitShift(object):
	def __init__(self, shift_bit_cnt, data):
		self.amt = int(shift_bit_cnt)
		self.data = list(data)
		assert all(0 <= d < 256 for d in data)
		if (self.data[-1] == 0):
			min_shift = 0
		else:
			min_shift = int(ceil(log(abs(self.data[-1]), 2)))
		min_shift += 8 * (len(self.data) - 1)
		if self.amt < min_shift:
			raise InvalidShiftSize('shift {} too short for data {} (minimum {})'.format(
				self.amt, self.data, min_shift))


def bin_to_txt(bytes):
	txt = ''
	is_first = True
	for byt in bytes:
		if is_first:
			is_first = False
		else:
			txt += '_'
		txt += '{0:d}{1:d}{2:d}{3:d}{4:d}{5:d}{6:d}{7:d}'.format(
			byt & 128 != 0,
			byt & 64 != 0,
			byt & 32 != 0,
			byt & 16 != 0,
			byt & 8 != 0,
			byt & 4 != 0,
			byt & 2 != 0,
			byt & 1 != 0,
		)
	return txt


if __name__ == '__main__':
	#todo: remove
	print(nr_to_bintxt(25))
	print(nr_to_bintxt(115))
	print(nr_to_bintxt(922337213615477180794186))


