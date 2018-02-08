
edges = tuple(range(100))

def combine(already, depthleft):
	alreadyset = set(already)
	for q in edges:
		if q in alreadyset:
			continue
		if depthleft <= 0:
			yield already + (q,)
		else:
			for c in combine(already + (q,), depthleft - 1):
				yield c


if __name__ == '__main__':
	for c in combine((), 2):
		print(c)


