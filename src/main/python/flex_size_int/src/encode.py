
from math import log, floor, ceil
from struct import Struct

from src.main.python.flex_size_int.src.utils import bin_to_txt

packer = Struct('!q')  #todo: not used

def long_to_bin8(nr):
	#todo: not used
	nr =  int(nr)
	bitcnt = int(floor(log(nr, 2) + 1))
	fullbin = packer.pack(nr)
	shortbin = fullbin[len(fullbin) - (bitcnt - 1) // 8 - 1:]
	return bitcnt, shortbin


def bin8_to_flex(cnt, bin):
	#todo: not used
	blockcnt = int(ceil(cnt / 7))
	print(bin)
	print(bin[-1] % 128)
	print(cnt, blockcnt)


tobit = Struct('!b')


def long_to_flex(nr):
	anr = abs(int(nr))
	if (anr > 2**63):
		raise NotImplementedError("numbers bigger than Long cannot be encoded yet, because bit-shift is not exact for such values (value: {})".format(nr))
	if anr == 0:
		blockcnt = 1
	else:
		bitcnt = int(floor(log(anr, 2))) + 2
		blockcnt = int(ceil(bitcnt / 7))
	bytes = [None] * blockcnt
	# All except first blocks have 7 bits
	for blocknr in range(blockcnt - 1, 0, -1):
		bytes[blocknr] = tobit.pack(anr % 128)[0]
		anr >>= 7
	# First block has 6 or fewer bits
	bytes[0] = tobit.pack(anr % 64)[0]
	anr >>= 6
	# Set the first bit of last byte to 1
	bytes[-1] += 1 << 7
	# Set the second bit of the first byte to 1 if negative
	if nr < 0:
		bytes[0] += 1 << 6
	return bytes


if __name__ == '__main__':
	# print(bin_to_txt(long_to_bin8(int(2**35))[-1]))
	# print(bin_to_txt(long_to_bin8(int(2**7))[-1]))
	# print(bin_to_txt(long_to_bin8(int(2**8 - 1))[-1]))
	# print(bin_to_txt(long_to_bin8(int(2**8))[-1]))
	# print(bin_to_txt(long_to_bin8(int(25))[-1]))
	# print('---')
	# print(bin8_to_flex(*long_to_bin8(25)))
	# print(bin8_to_flex(*long_to_bin8(int(2**(5*7-1)))))
	# print(bin8_to_flex(*long_to_bin8(int(2**(5*7)))))
	print(bin_to_txt(long_to_flex(25)))
	print(bin_to_txt(long_to_flex(115)))
	print(bin_to_txt(long_to_flex(-115)))
	print(bin_to_txt(long_to_flex(0)))
	print(bin_to_txt(long_to_flex(129)))
	# print(720575940379279359995*128 + 92233720368547758079418%128 - 92233720368547758079418)
	# print('>>' + str(92233720368547758079418%64))
	print(bin_to_txt(long_to_flex(58)))
	print(bin_to_txt(long_to_flex(-413177)))
	print(bin_to_txt(long_to_flex(9_223_372_036_854_775_807)))
	# print(bin_to_txt(long_to_flex(92233720368547758079418)))
	# print('10000110_00001101_00100000_00000000_00010001_01010001_01101001_00000011_00010110_00011100_00001010_11001010')


