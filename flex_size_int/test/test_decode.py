from pytest import raises

from decode import flex_to_long, flex_to_ulong
from utils import LASTBYTE, POSITIVE, NEGATIVE, DATAPERBYTE, InvalidFlexInt


def test_flex_to_long():
	assert 0 == flex_to_long([POSITIVE + LASTBYTE])
	assert 25 == flex_to_long([POSITIVE + LASTBYTE + 25])
	assert 58 == flex_to_long([POSITIVE + LASTBYTE + 58])
	assert 115 == flex_to_long([POSITIVE + 0, LASTBYTE + 115])
	assert -115 == flex_to_long([NEGATIVE, LASTBYTE + 115])
	assert -413177 == flex_to_long([NEGATIVE + 25, 27, LASTBYTE + 121])
	assert 9223372036854775807 == flex_to_long([0, 127, 127, 127, 127, 127, 127, 127, 127, LASTBYTE + 127])
	""" Cover edge cases """
	for (signbit, sign) in ((POSITIVE, +1), (NEGATIVE, -1)):
		assert sign * 63 == flex_to_long([signbit + LASTBYTE + 63])
		assert sign * 64 == flex_to_long([signbit + 0, LASTBYTE + 64])
		assert sign * 127 == flex_to_long([signbit + 0, LASTBYTE + 127])
		assert sign * 128 == flex_to_long([signbit + 1, LASTBYTE])
		assert sign * 129 == flex_to_long([signbit + 1, LASTBYTE + 1])
		assert sign * 255 == flex_to_long([signbit + 1, LASTBYTE + DATAPERBYTE - 1])
		assert sign * 256 == flex_to_long([signbit + 2, LASTBYTE])
		assert sign * 512 == flex_to_long([signbit + 4, LASTBYTE])
		assert sign * 8191 == flex_to_long([signbit + 63, LASTBYTE + DATAPERBYTE - 1])
		assert sign * 8192 == flex_to_long([signbit, 8192 // (2 ** 7), LASTBYTE])
		assert sign * 16383 == flex_to_long([signbit, DATAPERBYTE - 1, LASTBYTE + DATAPERBYTE - 1])
		assert sign * 16384 == flex_to_long([signbit + 1, 0, LASTBYTE])


def test_invalid_flex_to_long():
	with raises(InvalidFlexInt):
		flex_to_long([LASTBYTE + NEGATIVE])


def test_flex_to_ulong():
	assert 0 == flex_to_ulong([LASTBYTE])
	assert 63 == flex_to_ulong([63 + LASTBYTE])
	assert 64 == flex_to_ulong([64 + LASTBYTE])
	assert 127 == flex_to_ulong([127 + LASTBYTE])
	assert 128 == flex_to_ulong([1, LASTBYTE])
	assert 16383 == flex_to_ulong([DATAPERBYTE - 1, DATAPERBYTE - 1 + LASTBYTE])
	assert 16384 == flex_to_ulong([1, 0, LASTBYTE])
	assert 2**24 == flex_to_ulong([8, 0, 0, LASTBYTE])
	assert 2**49 == flex_to_ulong([1, 0, 0, 0, 0, 0, 0, LASTBYTE])


