from functools import reduce


def start_of_packet(signal, marker_chars):
    for index in range(len(signal)):
        unique_buffer = reduce(lambda x, y: x if y in x else x + y, signal[index:index + marker_chars], '')

        if len(unique_buffer) == marker_chars:
            return index + marker_chars

    return len(signal)


def main():
    signal = open("day06/input.txt", "r").read()
    print("Part 1: ", start_of_packet(signal, 4))
    print("Part 2: ", start_of_packet(signal, 14))


main()