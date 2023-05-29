def getItemValue(item):
    if item.islower():
        return ord(item) - 96

    return ord(item) - 38


def findSharedItem(bags):
    return set(bags[0]).intersection(*map(set, bags)).pop()


def main():
    input = [i for i in open("day03/input.txt", "r").read().split('\n')]

    # Part 1
    rucksacks = [[i[int(len(i) / 2):], i[:int(len(i) / 2)]] for i in input]
    print("Part 1: ", sum(map(getItemValue, [findSharedItem(bags) for bags in rucksacks])))

    # Part
    print("Part 2: ", sum(map(getItemValue, [findSharedItem(input[i:i+3]) for i in range(0, len(input), 3)])))


main()