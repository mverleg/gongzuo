
from encode import long_to_flex, ulong_to_flex
from utils import LASTBYTE, NEGATIVE, DATAPERBYTE, bin_to_txt


def test_long_to_flex():
	assert long_to_flex(0) == [LASTBYTE]
	assert long_to_flex(25) == [LASTBYTE + 25]
	assert long_to_flex(58) == [LASTBYTE + 58]
	assert long_to_flex(129) == [1, LASTBYTE + 1]
	assert long_to_flex(115) == [0, LASTBYTE + 115]
	assert long_to_flex(-115) == [NEGATIVE, LASTBYTE + 115]
	assert long_to_flex(-413177) == [NEGATIVE + 25, 27, LASTBYTE + 121]
	assert long_to_flex(9_223_372_036_854_775_807) == [0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127]


def test_ulong_to_flex():
	assert ulong_to_flex(0) == [LASTBYTE]
	assert ulong_to_flex(63) == [63 + LASTBYTE]
	assert ulong_to_flex(64) == [64 + LASTBYTE]
	assert ulong_to_flex(127) == [127 + LASTBYTE]
	assert ulong_to_flex(16383) == [DATAPERBYTE - 1, DATAPERBYTE - 1 + LASTBYTE]
	assert ulong_to_flex(16384) == [1, 0, LASTBYTE]
	assert ulong_to_flex(2**24) == [8, 0, 0, LASTBYTE]
	assert ulong_to_flex(2**49) == [1, 0, 0, 0, 0, 0, 0, LASTBYTE]


