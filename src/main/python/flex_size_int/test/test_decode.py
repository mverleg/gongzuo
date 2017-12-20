
from decode import flex_to_long
from utils import LASTBYTE, NEGATIVE


def test_flex_to_long():
	assert flex_to_long([LASTBYTE]) == 0
	assert flex_to_long([LASTBYTE + 25]) == 25
	assert flex_to_long([LASTBYTE + 58]) == 58
	assert flex_to_long([1, LASTBYTE + 1]) == 129
	assert flex_to_long([0, LASTBYTE + 115]) == 115
	assert flex_to_long([NEGATIVE, LASTBYTE + 115]) == -115
	assert flex_to_long([NEGATIVE + 25, 27, LASTBYTE + 121]) == -413177
	assert flex_to_long([0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127]) == 9_223_372_036_854_775_807


def test_invalid_flex_to_long():
	raise NotImplementedError("-0")


