import os

puma = [el for el in os.listdir('./programs/puma') if '$' not in el and 'META' not in el]
puma.sort()

brown = [el for el in os.listdir('./Programs/brown/edu/brown/cs/mapreduce/benchmarks') if '$' not in el and 'META' not in el and 'benchmark3' not in el]
brown.sort()

pigmix2 = [el for el in os.listdir('./programs/pigmix2') if '$' not in el and 'META' not in el]
pigmix2.sort()




fh = open('things.java', 'w+')
fh.write('    static final String[] PUMA_CLASSES = new String[] {\n')
for el in puma:

    fh.write('            \"{}\"'.format(el.split('.class')[0]))
    if puma.index(el) == len(puma) - 1:
        fh.write('\n')
    else:
        fh.write(',\n')
fh.write('    };\n')

base_brown = 'brown.edu.brown.cs.mapreduce.benchmarks.{}'

fh.write('    static final String[] BROWN_CLASSES = new String[] {\n')
for el in brown:
    brown_name = base_brown.format(el.split('.class')[0])
    fh.write('            \"{}\"'.format(brown_name))
    if brown.index(el) == len(brown) - 1:
        fh.write('\n')
    else:
        fh.write(',\n')
fh.write('    };\n')

fh.write('    static final String[] PIGMIX2_CLASSES = new String[] {\n')
for el in pigmix2:

    fh.write('            \"{}\"'.format(el.split('.class')[0]))
    if pigmix2.index(el) == len(pigmix2) - 1:
        fh.write('\n')
    else:
        fh.write(',\n')
fh.write('    };\n')
fh.close()

