rounds = [i.split(' ') for i in open("day02/input.txt", "r").read().split('\n')]


winners = {
    1: 3,
    2: 1,
    3: 2
}


def convert(item):
    match item:
        case 'A' | 'X':
            return 1
        case 'B' | 'Y':
            return 2
        case _:
            return 3


def score(player, opponent):
    if player == opponent:
        return 3
    if winners.get(player) is opponent:
        return 6

    return 0


def scoreStrategy(player, opponent):
    if player == 1:
        return 0 + winners.get(opponent)
    if player == 2:
        return 3 + opponent

    return 6 + list(winners.keys())[list(winners.values()).index(opponent)]


# Part 1
print("Part 1: ", sum([score(convert(i[1]), convert(i[0])) + convert(i[1]) for i in rounds]))

# Part 2
# print(list(winners.keys())[list(winners.values()).index(3)])
print("Part 2: ", sum([scoreStrategy(convert(i[1]), convert(i[0])) for i in rounds]))