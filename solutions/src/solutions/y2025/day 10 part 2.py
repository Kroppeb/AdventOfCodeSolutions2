from tabnanny import check

import z3


def solve(buttons, target):

    counts = [z3.Int(f"{i}") for i in range(len(buttons))]

    s = z3.Solver()
    for i in counts:
        s.add(i >= 0)

    sm = [0 for _ in target]
    for i, button in zip(counts, buttons, strict=True):
        sm = [x + i if j in button else x for j, x in enumerate(sm)]


    for x, t in zip(sm, target):
        s.add(x == t)


    for i in range(1, sum(target) + 1):
        s.push()
        s.add(sum(counts) <= i)
        if s.check().r > 0:
            s.pop()
            return i
        s.pop()




print(solve(
    [(1, 3, 5), (0, 2, 5), (1, 3), (2, 3, 5), (0, 2, 4), (0, 1, 2, 4)],
    [44, 25, 63, 29, 33, 40],
))

print(solve([(3,), (1, 3), (2,), (2, 3), (0, 2), (0, 1)], [3, 5, 4, 7]))


data = """
<REDACTED>
"""

count = 0
for line in data.split("\n"):
    line = line.strip()
    if not line: continue
    print(line)
    _, *b, t = line.split()
    buttons = [[int(i) for i in bb[1:-1].split(",")] for bb in b]
    target = [int(i) for i in t[1:-1].split(",")]
    count += solve(buttons, target)

print(count)