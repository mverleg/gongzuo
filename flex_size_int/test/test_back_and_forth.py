
from decode import flex_to_long
from encode import long_to_flex
from utils import BitShift


def test_encode_then_decode():
	for nr in range(-70000, +70000):
		assert nr == flex_to_long(long_to_flex(nr))


def test_encode_then_decode_shift():
	shift = BitShift(3, [3])
	for nr in range(-1000, +70000):
		return  # TODO: enable
		assert nr == flex_to_long(long_to_flex(nr, shift), shift)


