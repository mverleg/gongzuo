from pytest import raises

from decode import flex_to_long, flex_to_ulong
from utils import LASTBYTE, NEGATIVE, DATAPERBYTE, InvalidFlexInt


def test_flex_to_long():
	assert flex_to_long([LASTBYTE]) == 0
	assert flex_to_long([LASTBYTE + 25]) == 25
	assert flex_to_long([LASTBYTE + 58]) == 58
	#TODO: add more border cases like 63/64/65
	assert flex_to_long([1, LASTBYTE + 1]) == 129
	assert flex_to_long([0, LASTBYTE + 115]) == 115
	assert flex_to_long([NEGATIVE, LASTBYTE + 115]) == -115
	assert flex_to_long([NEGATIVE + 25, 27, LASTBYTE + 121]) == -413177
	assert flex_to_long([0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127]) == 9_223_372_036_854_775_807


def test_invalid_flex_to_long():
	with raises(InvalidFlexInt):
		flex_to_long([LASTBYTE + NEGATIVE])


def test_flex_to_ulong():
	assert flex_to_ulong([LASTBYTE]) == 0
	assert flex_to_ulong([63 + LASTBYTE]) == 63
	assert flex_to_ulong([64 + LASTBYTE]) == 64
	assert flex_to_ulong([127 + LASTBYTE]) == 127
	assert flex_to_ulong([DATAPERBYTE - 1, DATAPERBYTE - 1 + LASTBYTE]) == 16383
	assert flex_to_ulong([1, 0, LASTBYTE]) == 16384
	assert flex_to_ulong([8, 0, 0, LASTBYTE]) == 2**24
	assert flex_to_ulong([1, 0, 0, 0, 0, 0, 0, LASTBYTE]) == 2**49


