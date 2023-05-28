import re

assignmentPairs = [list(map(int, re.split("\W+", i))) for i in open("day04/input.txt", "r").read().split('\n')]


def overlaps(assignment):
    if assignment[1] - assignment[0] < assignment[3] - assignment[2]:
        return (assignment[0] >= assignment[2]) and (assignment[1] <= assignment[3])

    return (assignment[0] <= assignment[2]) and (assignment[1] >= assignment[3])


def overlapsAtAll(assignment):
    return max(assignment[0], assignment[2]) <= min(assignment[1], assignment[3])


# Part 1
print("Part 1: ", [overlaps(assignment) for assignment in assignmentPairs].count(True))

# Part 2
print("Part 2: ", [overlapsAtAll(assignment) for assignment in assignmentPairs].count(True))
