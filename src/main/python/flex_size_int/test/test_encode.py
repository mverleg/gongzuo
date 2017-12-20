
from encode import long_to_flex
from utils import LASTBYTE, NEGATIVE


def test_long_to_flex():
	assert long_to_flex(0) == [LASTBYTE]
	assert long_to_flex(25) == [LASTBYTE + 25]
	assert long_to_flex(58) == [LASTBYTE + 58]
	assert long_to_flex(129) == [1, LASTBYTE + 1]
	assert long_to_flex(115) == [0, LASTBYTE + 115]
	assert long_to_flex(-115) == [NEGATIVE, LASTBYTE + 115]
	assert long_to_flex(-413177) == [NEGATIVE + 25, 27, LASTBYTE + 121]
	assert long_to_flex(9_223_372_036_854_775_807) == [0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127]


