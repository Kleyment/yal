# Yal

## Auteurs
Clément Bellanger, Pierre Génard, Valentin Thouvenin

## Des tests
```bash
#!/bin/bash

for i in `seq 30`; do sep="$sep="; done

for yal in $(ls *.yal)
do
  echo $sep
  echo $yal
  java -jar yal.jar $yal
  [ $? -eq 0 ] && java -jar Mars4_5.jar dec v1 ${yal//yal/mips}
done

exit 0
```

## Un moyen automatisé de tester
Exemple 1.test contient

0_0_Constante.yal
COMPILATION OK
0x00000001

1ère ligne fichier test complet
2ème ligne bout de sortie attendu apres compilation
3ème ligne bout de sortie attendu apres execution


```bash
#!/bin/bash
MARS_PATH="."
YAL_PATH="."
for i in $(ls *.test)
do
    YAL_TEST_NAME=$(cat $i | sed '1q;d')
    
    YAL_TEST_OUTPUT=$(cat $i | sed '2q;d')
    YAL_OUTPUT=$(java -jar $YAL_PATH/yal.jar $YAL_TEST_NAME 2>&1)

    echo $YAL_TEST_NAME
    echo $YAL_OUTPUT | grep -q "$YAL_TEST_OUTPUT" && echo "OK" || echo "PAS OK"
    
    if [ "$YAL_OUTPUT" == "COMPILATION OK" ]
    then
	MIPS_TEST_OUTPUT=$(cat $i | sed '3q;d')
	MIPS_TEST_NAME=$(echo $YAL_TEST_NAME | sed "s/.yal/.mips/g")
	MIPS_OUTPUT=$(java -jar $MARS_PATH/Mars4_5.jar v1 $MIPS_TEST_NAME)

	echo $MIPS_OUTPUT | grep -q "$MIPS_TEST_OUTPUT" && echo "OK" || echo "PAS OK"
    fi
done

```


## Assembleur MIPS
- [Télécharger MARS](http://courses.missouristate.edu/KenVollmar/MARS/download.htm)
- [Utiliser MARS en ligne de commande](https://courses.missouristate.edu/KenVollmar/mars/Help/MarsHelpCommand.html)

## Documentation MIPS
- [MIPS Assembly Instructions](https://www2.cs.duke.edu/courses/fall13/compsci250/MIPS-ASM.pdf)
- [MIPS Assembly Language](http://service.scs.carleton.ca/sivarama/org_book/org_book_web/slides/chap_1_versions/ch15_1.pdf)
- [MIPS Instruction Reference](http://www.mrc.uidaho.edu/mrc/people/jff/digital/MIPSir.html)
- [MIPS Summary](http://www.cs.tufts.edu/comp/140/lectures/Day_3/mips_summary.pdf)

## Feuille de route
- **YAL0** --> Mardi 30 Janvier
- **YAL1** --> Mardi 13 Février
- **YAL2** --> Mardi 6 Mars
- **YAL3** --> Mardi 20 Mars
- **YAL4** --> Mardi 27 Mars
- **YAL5** --> Vendredi 6 Avril


