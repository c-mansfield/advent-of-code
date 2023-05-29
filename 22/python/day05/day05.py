import collections
import copy


def build_stacks(initial):
    stacks_dict = collections.defaultdict(list)
    for row in initial:
        cleaned = row.replace("    ", "[0]").replace(" ", "").replace("[", "").replace("]", "")
        for i, char in enumerate(cleaned):
            if not char.isnumeric():
                stacks_dict[i + 1].append(char)

    return stacks_dict


def rearrange(rearranged_stack, instructions, upgraded_crane):
    for instruction in instructions:
        items, initialRow, endRow = [int(i) for i in instruction.split(" ") if i.isnumeric()]
        rearranged_stack[endRow][:0] = rearranged_stack[initialRow][:items] if upgraded_crane else list(reversed(rearranged_stack[initialRow][:items]))
        del rearranged_stack[initialRow][:items]

    return rearranged_stack


def find_top_crates(rearranged_stacks):
    top_crates = [rearranged_stacks[i][0] for i in range(1, len(rearranged_stacks) + 1) if rearranged_stacks[i]]
    return ''.join(top_crates)


def main():
    input = open("day05/input.txt", "r").read().split('\n')
    stacks, instructions = build_stacks(input[:input.index('')]), list(filter(None, input[input.index(''):]))

    print("Part 1: ", find_top_crates(rearrange(copy.deepcopy(stacks), instructions, False)))
    print("Part 2: ", find_top_crates(rearrange(copy.deepcopy(stacks), instructions, True)))


main()