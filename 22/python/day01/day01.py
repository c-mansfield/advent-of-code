def main():
    elves = [i.split('\n') for i in open("day01/input.txt", "r").read().split('\n\n')]

    # Part 1
    print("Part 1: ", max([sum(map(int, elf)) for elf in elves]))

    # Part 2
    print("Part 2: ", sum(sorted([sum(map(int, elf)) for elf in elves])[-3:]))

main()
