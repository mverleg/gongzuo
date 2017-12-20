
DATAPERBYTE = LASTBYTE = 1 << 7
NEGATIVE = 1 << 6


def nr_to_bintxt(nr):
	txt = ''
	# Calculate integer power
	twofac = 1
	while twofac <= nr:
		twofac *= 2
	# Find the binary digits
	while twofac > 1:
		print(twofac)
		twofac //= 2
		if nr >= twofac:
			txt += '1'
			nr -= twofac
		else:
			txt += '0'
	# Add underscores for readability
	ustxt = ''
	while len(txt) > 7:
		ustxt = '_' + txt[-7:] + ustxt
		txt = txt[:-7]
	ustxt = txt + ustxt
	return ustxt


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



