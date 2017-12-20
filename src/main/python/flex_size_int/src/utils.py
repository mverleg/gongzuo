

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


print(nr_to_bintxt(25))
print(nr_to_bintxt(115))
print(nr_to_bintxt(922337213615477180794186))



