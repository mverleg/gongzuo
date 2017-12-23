
from pytest import raises
from utils import BitShift, InvalidShiftSize


def test_shift_cls():
	shift = BitShift(3, [7])
	assert shift.amt == 3
	assert shift.data == [7]
	with raises(InvalidShiftSize):
		BitShift(2, [7])
	BitShift(4, [7])
	BitShift(19, [1, 0, 7])
	with raises(InvalidShiftSize):
		BitShift(18, [1, 0, 7])
	BitShift(20, [1, 0, 7])


