package com.bbou.material.builder

val GPICK_COLOR_MAP: Map<Int, String> = mapOf(
    0x8ffe09 to "acid green", // -7340535
    0xbd6c48 to "adobe", // -4363192
    0x54ac68 to "algae", // -11228056
    0x21c36f to "algae green", // -14564497
    0x070d0d to "almost black", // -16315123
    0xfeb308 to "amber", // -85240
    0x9b5fc0 to "amethyst", // -6594624
    0x6ecb3c to "apple", // -9516228
    0x76cd26 to "apple green", // -8991450
    0xffb16d to "apricot", // -20115
    0x13eac9 to "aqua", // -15471927
    0x02d8e9 to "aqua blue", // -16590615
    0x12e193 to "aqua green", // -15539821
    0x2ee8bb to "aqua marine", // -13702981
    0x04d8b2 to "aquamarine", // -16459598
    0x4b5d16 to "army green", // -11838186
    0x77ab56 to "asparagus", // -8934570
    0x3d0734 to "aubergine", // -12777676
    0x9a3001 to "auburn", // -6672383
    0x90b134 to "avocado", // -7294668
    0x87a922 to "avocado green", // -7886558
    0x1d5dec to "azul", // -14852628
    0x069af3 to "azure", // -16344333
    0xa2cffe to "baby blue", // -6107138
    0x8cff9e to "baby green", // -7536738
    0xffb7ce to "baby pink", // -18482
    0xab9004 to "baby poo", // -5533692
    0x937c00 to "baby poop", // -7111680
    0x8f9805 to "baby poop green", // -7366651
    0xb6c406 to "baby puke green", // -4799482
    0xca9bf7 to "baby purple", // -3499017
    0xad900d to "baby shit brown", // -5402611
    0x889717 to "baby shit green", // -7825641
    0xffff7e to "banana", // -130
    0xfafe4b to "banana yellow", // -328117
    0xfe46a5 to "barbie pink", // -112987
    0x94ac02 to "barf green", // -7033854
    0xac1db8 to "barney", // -5497416
    0xa00498 to "barney purple", // -6290280
    0x6b7c85 to "battleship grey", // -9732987
    0xe6daa6 to "beige", // -1647962
    0x990f4b to "berry", // -6746293
    0xb5c306 to "bile", // -4865274
    0x000000 to "black", // -16777216
    0xafa88b to "bland", // -5265269
    0x770001 to "blood", // -8978431
    0xfe4b03 to "blood orange", // -111869
    0x980002 to "blood red", // -6815742
    0x0343df to "blue", // -16563233
    0x2242c7 to "blue blue", // -14531897
    0x137e6d to "blue green", // -15499667
    0x607c8e to "blue grey", // -10453874
    0x5729ce to "blue purple", // -11064882
    0x5d06e9 to "blue violet", // -10680599
    0x533cc6 to "blue with a hint of purple", // -11322170
    0x0f9b8e to "blue/green", // -15754354
    0x758da3 to "blue/grey", // -9073245
    0x5a06ef to "blue/purple", // -10877201
    0x464196 to "blueberry", // -12172906
    0x017a79 to "bluegreen", // -16680327
    0x85a3b2 to "bluegrey", // -8019022
    0x2bb179 to "bluey green", // -13913735
    0x89a0b0 to "bluey grey", // -7757648
    0x6241c7 to "bluey purple", // -10337849
    0x2976bb to "bluish", // -14059845
    0x10a674 to "bluish green", // -15686028
    0x748b97 to "bluish grey", // -9139305
    0x703be7 to "bluish purple", // -9421849
    0x5539cc to "blurple", // -11191860
    0xf29e8e to "blush", // -876914
    0xfe828c to "blush pink", // -97652
    0x9bb53c to "booger", // -6572740
    0x96b403 to "booger green", // -6900733
    0x7b002c to "bordeaux", // -8716244
    0x63b365 to "boring green", // -10243227
    0x044a05 to "bottle green", // -16496123
    0xa03623 to "brick", // -6277597
    0xc14a09 to "brick orange", // -4109815
    0x8f1402 to "brick red", // -7400446
    0x0bf9ea to "bright aqua", // -15992342
    0x0165fc to "bright blue", // -16685572
    0x41fdfe to "bright cyan", // -12452354
    0x01ff07 to "bright green", // -16646393
    0xc760ff to "bright lavender", // -3710721
    0x26f7fd to "bright light blue", // -14223363
    0x2dfe54 to "bright light green", // -13762988
    0xc95efb to "bright lilac", // -3580165
    0x87fd05 to "bright lime", // -7865083
    0x65fe08 to "bright lime green", // -10093048
    0xff08e8 to "bright magenta", // -63256
    0x9cbb04 to "bright olive", // -6505724
    0xff5b00 to "bright orange", // -42240
    0xfe01b1 to "bright pink", // -130639
    0xbe03fd to "bright purple", // -4324355
    0xff000d to "bright red", // -65523
    0x05ffa6 to "bright sea green", // -16384090
    0x02ccfe to "bright sky blue", // -16593666
    0x01f9c6 to "bright teal", // -16647738
    0x0ffef9 to "bright turquoise", // -15728903
    0xad0afd to "bright violet", // -5436675
    0xfffd01 to "bright yellow", // -767
    0x9dff00 to "bright yellow green", // -6422784
    0x05480d to "british racing green", // -16431091
    0xa87900 to "bronze", // -5736192
    0x653700 to "brown", // -10144000
    0x706c11 to "brown green", // -9409519
    0x8d8468 to "brown grey", // -7502744
    0xb96902 to "brown orange", // -4626174
    0x922b05 to "brown red", // -7197947
    0xb29705 to "brown yellow", // -5073147
    0x9c6d57 to "brownish", // -6525609
    0x6a6e09 to "brownish green", // -9802231
    0x86775f to "brownish grey", // -7964833
    0xcb7723 to "brownish orange", // -3442909
    0xc27e79 to "brownish pink", // -4030855
    0x76424e to "brownish purple", // -9026994
    0x9e3623 to "brownish red", // -6408669
    0xc9b003 to "brownish yellow", // -3559421
    0x6f6c0a to "browny green", // -9475062
    0xca6b02 to "browny orange", // -3511550
    0x7e4071 to "bruise", // -8503183
    0xff69af to "bubble gum pink", // -38481
    0xff6cb5 to "bubblegum", // -37707
    0xfe83cc to "bubblegum pink", // -97332
    0xfef69e to "buff", // -67938
    0x610023 to "burgundy", // -10420189
    0xc04e01 to "burnt orange", // -4174335
    0x9f2305 to "burnt red", // -6348027
    0xb75203 to "burnt siena", // -4763133
    0xb04e0f to "burnt sienna", // -5222897
    0xa0450e to "burnt umber", // -6273778
    0xd5ab09 to "burnt yellow", // -2774263
    0x6832e3 to "burple", // -9948445
    0xffff81 to "butter", // -127
    0xfffd74 to "butter yellow", // -652
    0xfdb147 to "butterscotch", // -151225
    0x4e7496 to "cadet blue", // -11635562
    0xc69f59 to "camel", // -3760295
    0x7f8f4e to "camo", // -8417458
    0x526525 to "camo green", // -11377371
    0x4b6113 to "camouflage green", // -11837165
    0xfdff63 to "canary", // -131229
    0xfffe40 to "canary yellow", // -448
    0xff63e9 to "candy pink", // -39959
    0xaf6f09 to "caramel", // -5279991
    0x9d0216 to "carmine", // -6487530
    0xfd798f to "carnation", // -165489
    0xff7fa7 to "carnation pink", // -32857
    0x8ab8fe to "carolina blue", // -7685890
    0xbefdb7 to "celadon", // -4260425
    0xc1fd95 to "celery", // -4063851
    0xa5a391 to "cement", // -5921903
    0xde0c62 to "cerise", // -2225054
    0x0485d1 to "cerulean", // -16480815
    0x056eee to "cerulean blue", // -16421138
    0x343837 to "charcoal", // -13354953
    0x3c4142 to "charcoal grey", // -12828350
    0xc1f80a to "chartreuse", // -4065270
    0xcf0234 to "cherry", // -3210700
    0xf7022a to "cherry red", // -589270
    0x742802 to "chestnut", // -9164798
    0x3d1c02 to "chocolate", // -12772350
    0x411900 to "chocolate brown", // -12510976
    0xac4f06 to "cinnamon", // -5484794
    0x680018 to "claret", // -9961448
    0xb66a50 to "clay", // -4822448
    0xb2713d to "clay brown", // -5082819
    0x247afd to "clear blue", // -14386435
    0xacc2d9 to "cloudy blue", // -5455143
    0x1e488f to "cobalt", // -14792561
    0x030aa7 to "cobalt blue", // -16577881
    0x875f42 to "cocoa", // -7905470
    0xa6814c to "coffee", // -5865140
    0x4984b8 to "cool blue", // -11959112
    0x33b864 to "cool green", // -13387676
    0x95a3a6 to "cool grey", // -6970458
    0xb66325 to "copper", // -4824283
    0xfc5a50 to "coral", // -239024
    0xff6163 to "coral pink", // -40605
    0x6a79f7 to "cornflower", // -9799177
    0x5170d7 to "cornflower blue", // -11439913
    0x9e003a to "cranberry", // -6422470
    0xffffc2 to "cream", // -62
    0xffffb6 to "creme", // -74
    0x8c000f to "crimson", // -7602161
    0xfffd78 to "custard", // -648
    0x00ffff to "cyan", // -16711681
    0xfedf08 to "dandelion", // -73976
    0x1b2431 to "dark", // -14998479
    0x05696b to "dark aqua", // -16422549
    0x017371 to "dark aquamarine", // -16682127
    0xac9362 to "dark beige", // -5467294
    0x00035b to "dark blue", // -16776357
    0x005249 to "dark blue green", // -16756151
    0x1f3b4d to "dark blue grey", // -14730419
    0x341c02 to "dark brown", // -13362174
    0xcf524e to "dark coral", // -3190194
    0xfff39a to "dark cream", // -3174
    0x0a888a to "dark cyan", // -16086902
    0x002d04 to "dark forest green", // -16765692
    0x9d0759 to "dark fuchsia", // -6486183
    0xb59410 to "dark gold", // -4877296
    0x388004 to "dark grass green", // -13074428
    0x033500 to "dark green", // -16567040
    0x1f6357 to "dark green blue", // -14720169
    0x363737 to "dark grey", // -13224137
    0x29465b to "dark grey blue", // -14072229
    0xd90166 to "dark hot pink", // -2555546
    0x1f0954 to "dark indigo", // -14743212
    0x9b8f55 to "dark khaki", // -6582443
    0x856798 to "dark lavender", // -8034408
    0x9c6da5 to "dark lilac", // -6525531
    0x84b701 to "dark lime", // -8079615
    0x7ebd01 to "dark lime green", // -8471295
    0x960056 to "dark magenta", // -6946730
    0x3c0008 to "dark maroon", // -12845048
    0x874c62 to "dark mauve", // -7910302
    0x48c072 to "dark mint", // -12009358
    0x20c073 to "dark mint green", // -14630797
    0xa88905 to "dark mustard", // -5732091
    0x000435 to "dark navy", // -16776139
    0x00022e to "dark navy blue", // -16776658
    0x373e02 to "dark olive", // -13156862
    0x3c4d03 to "dark olive green", // -12825341
    0xc65102 to "dark orange", // -3780350
    0x56ae57 to "dark pastel green", // -11096489
    0xde7e5d to "dark peach", // -2195875
    0x665fd1 to "dark periwinkle", // -10068015
    0xcb416b to "dark pink", // -3456661
    0x3f012c to "dark plum", // -12648148
    0x35063e to "dark purple", // -13302210
    0x840000 to "dark red", // -8126464
    0xb5485d to "dark rose", // -4896675
    0x02066f to "dark royal blue", // -16644497
    0x598556 to "dark sage", // -10910378
    0xc85a53 to "dark salmon", // -3646893
    0xa88f59 to "dark sand", // -5730471
    0x11875d to "dark sea green", // -15628451
    0x1fb57a to "dark seafoam", // -14699142
    0x3eaf76 to "dark seafoam green", // -12669066
    0x448ee4 to "dark sky blue", // -12284188
    0x214761 to "dark slate blue", // -14596255
    0xaf884a to "dark tan", // -5273526
    0x7f684e to "dark taupe", // -8427442
    0x014d4e to "dark teal", // -16691890
    0x045c5a to "dark turquoise", // -16491430
    0x34013f to "dark violet", // -13369025
    0xd5b60a to "dark yellow", // -2771446
    0x728f02 to "dark yellow green", // -9269502
    0x030764 to "darkblue", // -16578716
    0x054907 to "darkgreen", // -16430841
    0x014182 to "darkish blue", // -16694910
    0x287c37 to "darkish green", // -14123977
    0xda467d to "darkish pink", // -2472323
    0x751973 to "darkish purple", // -9102989
    0xa90308 to "darkish red", // -5700856
    0x08787f to "deep aqua", // -16222081
    0x040273 to "deep blue", // -16514445
    0x410200 to "deep brown", // -12516864
    0x02590f to "deep green", // -16623345
    0x8d5eb7 to "deep lavender", // -7512393
    0x966ebd to "deep lilac", // -6918467
    0xa0025c to "deep magenta", // -6290852
    0xdc4d01 to "deep orange", // -2339583
    0xcb0162 to "deep pink", // -3473054
    0x36013f to "deep purple", // -13237953
    0x9a0200 to "deep red", // -6684160
    0xc74767 to "deep rose", // -3717273
    0x015482 to "deep sea blue", // -16690046
    0x0d75f8 to "deep sky blue", // -15895048
    0x00555a to "deep teal", // -16755366
    0x017374 to "deep turquoise", // -16682124
    0x490648 to "deep violet", // -11991480
    0x3b638c to "denim", // -12885108
    0x3b5b92 to "denim blue", // -12887150
    0xccad60 to "desert", // -3363488
    0x9f8303 to "diarrhea", // -6323453
    0x8a6e45 to "dirt", // -7705019
    0x836539 to "dirt brown", // -8166087
    0x3f829d to "dirty blue", // -12615011
    0x667e2c to "dirty green", // -10060244
    0xc87606 to "dirty orange", // -3639802
    0xca7b80 to "dirty pink", // -3507328
    0x734a65 to "dirty purple", // -9221531
    0xcdc50a to "dirty yellow", // -3291894
    0x3e82fc to "dodger blue", // -12680452
    0x828344 to "drab", // -8223932
    0x749551 to "drab green", // -9136815
    0x4b0101 to "dried blood", // -11861759
    0xc3fbf4 to "duck egg blue", // -3933196
    0x49759c to "dull blue", // -11962980
    0x876e4b to "dull brown", // -7901621
    0x74a662 to "dull green", // -9132446
    0xd8863b to "dull orange", // -2587077
    0xd5869d to "dull pink", // -2783587
    0x84597e to "dull purple", // -8103554
    0xbb3f3f to "dull red", // -4505793
    0x5f9e8f to "dull teal", // -10510705
    0xeedc5b to "dull yellow", // -1123237
    0x4e5481 to "dusk", // -11643775
    0x26538d to "dusk blue", // -14265459
    0x475f94 to "dusky blue", // -12099692
    0xcc7a8b to "dusky pink", // -3376501
    0x895b7b to "dusky purple", // -7775365
    0xba6873 to "dusky rose", // -4560781
    0xb2996e to "dust", // -5072530
    0x5a86ad to "dusty blue", // -10844499
    0x76a973 to "dusty green", // -9000589
    0xac86a8 to "dusty lavender", // -5470552
    0xf0833a to "dusty orange", // -1014982
    0xd58a94 to "dusty pink", // -2782572
    0x825f87 to "dusty purple", // -8233081
    0xb9484e to "dusty red", // -4634546
    0xc0737a to "dusty rose", // -4164742
    0x4c9085 to "dusty teal", // -11759483
    0xa2653e to "earth", // -6134466
    0x8cfd7e to "easter green", // -7537282
    0xc071fe to "easter purple", // -4165122
    0xfeffca to "ecru", // -65590
    0xfffcc4 to "egg shell", // -828
    0x380835 to "eggplant", // -13105099
    0x430541 to "eggplant purple", // -12384959
    0xffffd4 to "eggshell", // -44
    0xc4fff7 to "eggshell blue", // -3866633
    0x0652ff to "electric blue", // -16362753
    0x21fc0d to "electric green", // -14550003
    0xa8ff04 to "electric lime", // -5701884
    0xff0490 to "electric pink", // -64368
    0xaa23ff to "electric purple", // -5626881
    0x01a049 to "emerald", // -16670647
    0x028f1e to "emerald green", // -16609506
    0x05472a to "evergreen", // -16431318
    0x658cbb to "faded blue", // -10122053
    0x7bb274 to "faded green", // -8670604
    0xf0944d to "faded orange", // -1010611
    0xde9dac to "faded pink", // -2187860
    0x916e99 to "faded purple", // -7246183
    0xd3494e to "faded red", // -2930354
    0xfeff7f to "faded yellow", // -65665
    0xcfaf7b to "fawn", // -3166341
    0x63a950 to "fern", // -10245808
    0x548d44 to "fern green", // -11236028
    0xfe0002 to "fire engine red", // -131070
    0x3c73a8 to "flat blue", // -12815448
    0x699d4c to "flat green", // -9855668
    0x08ff08 to "fluorescent green", // -16187640
    0x0aff02 to "fluro green", // -16056574
    0x90fda9 to "foam green", // -7275095
    0x0b5509 to "forest", // -16034551
    0x06470c to "forest green", // -16365812
    0x154406 to "forrest green", // -15383546
    0x436bad to "french blue", // -12358739
    0x69d84f to "fresh green", // -9840561
    0x58bc08 to "frog green", // -10961912
    0xed0dd9 to "fuchsia", // -1241639
    0xdbb40c to "gold", // -2378740
    0xf5bf03 to "golden", // -671997
    0xb27a01 to "golden brown", // -5080575
    0xf9bc08 to "golden rod", // -410616
    0xfec615 to "golden yellow", // -80363
    0xfac205 to "goldenrod", // -343547
    0x6c3461 to "grape", // -9685919
    0x5d1451 to "grape purple", // -10677167
    0xfd5956 to "grapefruit", // -173738
    0x5cac2d to "grass", // -10703827
    0x3f9b0b to "grass green", // -12608757
    0x419c03 to "grassy green", // -12477437
    0x15b01a to "green", // -15355878
    0x5edc1f to "green apple", // -10560481
    0x06b48b to "green blue", // -16337781
    0x544e03 to "green brown", // -11252221
    0x77926f to "green grey", // -8940945
    0x0cb577 to "green teal", // -15944329
    0xc9ff27 to "green yellow", // -3539161
    0x01c08d to "green/blue", // -16662387
    0xb5ce08 to "green/yellow", // -4862456
    0x23c48b to "greenblue", // -14433141
    0x40a368 to "greenish", // -12541080
    0xc9d179 to "greenish beige", // -3550855
    0x0b8b87 to "greenish blue", // -16020601
    0x696112 to "greenish brown", // -9871086
    0x2afeb7 to "greenish cyan", // -13959497
    0x96ae8d to "greenish grey", // -6902131
    0xbccb7a to "greenish tan", // -4404358
    0x32bf84 to "greenish teal", // -13451388
    0x00fbb0 to "greenish turquoise", // -16712784
    0xcdfd02 to "greenish yellow", // -3277566
    0x42b395 to "greeny blue", // -12405867
    0x696006 to "greeny brown", // -9871354
    0x7ea07a to "greeny grey", // -8478598
    0xc6f808 to "greeny yellow", // -3737592
    0x929591 to "grey", // -7170671
    0x6b8ba4 to "grey blue", // -9729116
    0x7f7053 to "grey brown", // -8425389
    0x789b73 to "grey green", // -8873101
    0xc3909b to "grey pink", // -3960677
    0x826d8c to "grey purple", // -8229492
    0x5e9b8a to "grey teal", // -10577014
    0x647d8e to "grey/blue", // -10191474
    0x86a17d to "grey/green", // -7954051
    0x77a1b5 to "greyblue", // -8937035
    0xa8a495 to "greyish", // -5725035
    0x5e819d to "greyish blue", // -10583651
    0x7a6a4f to "greyish brown", // -8754609
    0x82a67d to "greyish green", // -8214915
    0xc88d94 to "greyish pink", // -3633772
    0x887191 to "greyish purple", // -7835247
    0x719f91 to "greyish teal", // -9330799
    0xa0bf16 to "gross green", // -6242538
    0x536267 to "gunmetal", // -11312537
    0x8e7618 to "hazel", // -7440872
    0xa484ac to "heather", // -5995348
    0xd94ff5 to "heliotrope", // -2535435
    0x1bfc06 to "highlighter green", // -14943226
    0x9be5aa to "hospital green", // -6560342
    0x25ff29 to "hot green", // -14287063
    0xf504c9 to "hot magenta", // -719671
    0xff028d to "hot pink", // -64883
    0xcb00f5 to "hot purple", // -3473163
    0x0b4008 to "hunter green", // -16039928
    0xd6fffa to "ice", // -2686982
    0xd7fffe to "ice blue", // -2621442
    0x8fae22 to "icky green", // -7360990
    0x850e04 to "indian red", // -8057340
    0x380282 to "indigo", // -13106558
    0x3a18b1 to "indigo blue", // -12969807
    0x6258c4 to "iris", // -10331964
    0x019529 to "irish green", // -16673495
    0xffffcb to "ivory", // -53
    0x1fa774 to "jade", // -14702732
    0x2baf6a to "jade green", // -13914262
    0x048243 to "jungle green", // -16481725
    0x009337 to "kelley green", // -16739529
    0x02ab2e to "kelly green", // -16602322
    0x5cb200 to "kermit green", // -10702336
    0xaeff6e to "key lime", // -5308562
    0xaaa662 to "khaki", // -5593502
    0x728639 to "khaki green", // -9271751
    0x9cef43 to "kiwi", // -6492349
    0x8ee53f to "kiwi green", // -7412417
    0xc79fef to "lavender", // -3694609
    0x8b88f8 to "lavender blue", // -7632648
    0xdd85d7 to "lavender pink", // -2259497
    0x4da409 to "lawn green", // -11688951
    0x71aa34 to "leaf", // -9328076
    0x5ca904 to "leaf green", // -10704636
    0x51b73b to "leafy green", // -11421893
    0xac7434 to "leather", // -5475276
    0xfdff52 to "lemon", // -131246
    0xadf802 to "lemon green", // -5375998
    0xbffe28 to "lemon lime", // -4194776
    0xfdff38 to "lemon yellow", // -131272
    0x8fb67b to "lichen", // -7358853
    0x8cffdb to "light aqua", // -7536677
    0x7bfdc7 to "light aquamarine", // -8651321
    0xfffeb6 to "light beige", // -330
    0x95d0fc to "light blue", // -6958852
    0x7efbb3 to "light blue green", // -8455245
    0xb7c9e2 to "light blue grey", // -4732446
    0x76fda8 to "light bluish green", // -8979032
    0x53fe5c to "light bright green", // -11272612
    0xad8150 to "light brown", // -5406384
    0xa8415b to "light burgundy", // -5750437
    0xacfffc to "light cyan", // -5439492
    0x894585 to "light eggplant", // -7780987
    0x4f9153 to "light forest green", // -11562669
    0xfddc5c to "light gold", // -140196
    0x9af764 to "light grass green", // -6621340
    0x96f97b to "light green", // -6882949
    0x56fca2 to "light green blue", // -11076446
    0x63f7b4 to "light greenish blue", // -10225740
    0xd8dcd6 to "light grey", // -2564906
    0x9dbcd4 to "light grey blue", // -6439724
    0xb7e1a1 to "light grey green", // -4726367
    0x6d5acf to "light indigo", // -9610545
    0xe6f2a2 to "light khaki", // -1641822
    0xefc0fe to "light lavendar", // -1064706
    0xdfc5fe to "light lavender", // -2112002
    0xcafffb to "light light blue", // -3473413
    0xc8ffb0 to "light light green", // -3604560
    0xedc8ff to "light lilac", // -1193729
    0xaefd6c to "light lime", // -5309076
    0xb9ff66 to "light lime green", // -4587674
    0xfa5ff7 to "light magenta", // -368649
    0xa24857 to "light maroon", // -6141865
    0xc292a1 to "light mauve", // -4025695
    0xb6ffbb to "light mint", // -4784197
    0xa6fbb2 to "light mint green", // -5833806
    0xa6c875 to "light moss green", // -5846923
    0xf7d560 to "light mustard", // -535200
    0x155084 to "light navy", // -15380348
    0x2e5a88 to "light navy blue", // -13739384
    0x4efd54 to "light neon green", // -11600556
    0xacbf69 to "light olive", // -5456023
    0xa4be5c to "light olive green", // -5980580
    0xfdaa48 to "light orange", // -153016
    0xb2fba5 to "light pastel green", // -5047387
    0xc4fe82 to "light pea green", // -3867006
    0xffd8b1 to "light peach", // -10063
    0xc1c6fc to "light periwinkle", // -4077828
    0xffd1df to "light pink", // -11809
    0x9d5783 to "light plum", // -6465661
    0xbf77f6 to "light purple", // -4229130
    0xff474c to "light red", // -47284
    0xffc5cb to "light rose", // -14901
    0x3a2efe to "light royal blue", // -12964098
    0xbcecac to "light sage", // -4395860
    0xfea993 to "light salmon", // -87661
    0x98f6b0 to "light sea green", // -6752592
    0xa0febf to "light seafoam", // -6226241
    0xa7ffb5 to "light seafoam green", // -5767243
    0xc6fcff to "light sky blue", // -3736321
    0xfbeeac to "light tan", // -266580
    0x90e4c1 to "light teal", // -7281471
    0x7ef4cc to "light turquoise", // -8457012
    0xb36ff6 to "light urple", // -5017610
    0xd6b4fc to "light violet", // -2706180
    0xfffe7a to "light yellow", // -390
    0xccfd7f to "light yellow green", // -3342977
    0xc2ff89 to "light yellowish green", // -3997815
    0x7bc8f6 to "lightblue", // -8664842
    0x75fd63 to "lighter green", // -9044637
    0xa55af4 to "lighter purple", // -5940492
    0x76ff7b to "lightgreen", // -8978565
    0x3d7afd to "lightish blue", // -12748035
    0x61e160 to "lightish green", // -10362528
    0xa552e6 to "lightish purple", // -5942554
    0xfe2f4a to "lightish red", // -118966
    0xcea2fd to "lilac", // -3235075
    0xc48efd to "liliac", // -3895555
    0xaaff32 to "lime", // -5570766
    0x89fe05 to "lime green", // -7733755
    0xd0fe1d to "lime yellow", // -3080675
    0xd5174e to "lipstick", // -2812082
    0xc0022f to "lipstick red", // -4193745
    0xefb435 to "macaroni and cheese", // -1067979
    0xc20078 to "magenta", // -4063112
    0x4a0100 to "mahogany", // -11927296
    0xf4d054 to "maize", // -733100
    0xffa62b to "mango", // -22997
    0xfffa86 to "manilla", // -1402
    0xfcc006 to "marigold", // -212986
    0x042e60 to "marine", // -16503200
    0x01386a to "marine blue", // -16697238
    0x650021 to "maroon", // -10158047
    0xae7181 to "mauve", // -5344895
    0x2c6fbb to "medium blue", // -13865029
    0x7f5112 to "medium brown", // -8433390
    0x39ad48 to "medium green", // -12997304
    0x7d7f7c to "medium grey", // -8552580
    0xf36196 to "medium pink", // -826986
    0x9e43a2 to "medium purple", // -6405214
    0xff7855 to "melon", // -34731
    0x730039 to "merlot", // -9240519
    0x4f738e to "metallic blue", // -11570290
    0x276ab3 to "mid blue", // -14193997
    0x50a747 to "mid green", // -11491513
    0x03012d to "midnight", // -16580307
    0x020035 to "midnight blue", // -16646091
    0x280137 to "midnight purple", // -14155465
    0x667c3e to "military green", // -10060738
    0x7f4e1e to "milk chocolate", // -8434146
    0x9ffeb0 to "mint", // -6291792
    0x8fff9f to "mint green", // -7340129
    0x0bf77d to "minty green", // -15992963
    0x9d7651 to "mocha", // -6457775
    0x769958 to "moss", // -9004712
    0x658b38 to "moss green", // -10122440
    0x638b27 to "mossy green", // -10253529
    0x735c12 to "mud", // -9217006
    0x60460f to "mud brown", // -10467825
    0x606602 to "mud green", // -10459646
    0x886806 to "muddy brown", // -7837690
    0x657432 to "muddy green", // -10128334
    0xbfac05 to "muddy yellow", // -4215803
    0x920a4e to "mulberry", // -7206322
    0x6c7a0e to "murky green", // -9668082
    0xba9e88 to "mushroom", // -4546936
    0xceb301 to "mustard", // -3230975
    0xac7e04 to "mustard brown", // -5472764
    0xa8b504 to "mustard green", // -5720828
    0xd2bd0a to "mustard yellow", // -2966262
    0x3b719f to "muted blue", // -12881505
    0x5fa052 to "muted green", // -10510254
    0xd1768f to "muted pink", // -3049841
    0x805b87 to "muted purple", // -8365177
    0x70b23f to "nasty green", // -9391553
    0x01153e to "navy", // -16706242
    0x001146 to "navy blue", // -16772794
    0x35530a to "navy green", // -13282550
    0x04d9ff to "neon blue", // -16459265
    0x0cff0c to "neon green", // -15925492
    0xfe019a to "neon pink", // -130662
    0xbc13fe to "neon purple", // -4451330
    0xff073a to "neon red", // -63686
    0xcfff04 to "neon yellow", // -3145980
    0x107ab0 to "nice blue", // -15697232
    0x040348 to "night blue", // -16514232
    0x017b92 to "ocean", // -16680046
    0x03719c to "ocean blue", // -16551524
    0x3d9973 to "ocean green", // -12740237
    0xbf9b0c to "ocher", // -4220148
    0xbf9005 to "ochre", // -4222971
    0xc69c04 to "ocre", // -3761148
    0x5684ae to "off blue", // -11107154
    0x6ba353 to "off green", // -9723053
    0xffffe4 to "off white", // -28
    0xf1f33f to "off yellow", // -920769
    0xc77986 to "old pink", // -3704442
    0xc87f89 to "old rose", // -3637367
    0x6e750e to "olive", // -9538290
    0x645403 to "olive brown", // -10202109
    0x6f7632 to "olive drab", // -9472462
    0x677a04 to "olive green", // -9995772
    0xc2b709 to "olive yellow", // -4016375
    0xf97306 to "orange", // -429306
    0xbe6400 to "orange brown", // -4299776
    0xff6f52 to "orange pink", // -37038
    0xfd411e to "orange red", // -179938
    0xffad01 to "orange yellow", // -21247
    0xfd8d49 to "orangeish", // -160439
    0xfe420f to "orangered", // -114161
    0xb16002 to "orangey brown", // -5152766
    0xfa4224 to "orangey red", // -376284
    0xfdb915 to "orangey yellow", // -149227
    0xfc824a to "orangish", // -228790
    0xb25f03 to "orangish brown", // -5087485
    0xf43605 to "orangish red", // -772603
    0xc875c4 to "orchid", // -3639868
    0xfff9d0 to "pale", // -1584
    0xb8ffeb to "pale aqua", // -4653077
    0xd0fefe to "pale blue", // -3080450
    0xb1916e to "pale brown", // -5140114
    0xb7fffa to "pale cyan", // -4718598
    0xfdde6c to "pale gold", // -139668
    0xc7fdb5 to "pale green", // -3670603
    0xfdfdfe to "pale grey", // -131586
    0xeecffe to "pale lavender", // -1126402
    0xb1fc99 to "pale light green", // -5112679
    0xe4cbff to "pale lilac", // -1782785
    0xbefd73 to "pale lime", // -4260493
    0xb1ff65 to "pale lime green", // -5111963
    0xd767ad to "pale magenta", // -2660435
    0xfed0fc to "pale mauve", // -77572
    0xb9cc81 to "pale olive", // -4600703
    0xb1d27b to "pale olive green", // -5123461
    0xffa756 to "pale orange", // -22698
    0xffe5ad to "pale peach", // -6739
    0xffcfdc to "pale pink", // -12324
    0xb790d4 to "pale purple", // -4747052
    0xd9544d to "pale red", // -2534323
    0xfdc1c5 to "pale rose", // -147003
    0xffb19a to "pale salmon", // -20070
    0xbdf6fe to "pale sky blue", // -4327682
    0x82cbb2 to "pale teal", // -8205390
    0xa5fbd5 to "pale turquoise", // -5899307
    0xceaefa to "pale violet", // -3232006
    0xffff84 to "pale yellow", // -124
    0xfefcaf to "parchment", // -66385
    0xa2bffe to "pastel blue", // -6111234
    0xb0ff9d to "pastel green", // -5177443
    0xff964f to "pastel orange", // -27057
    0xffbacd to "pastel pink", // -17715
    0xcaa0ff to "pastel purple", // -3497729
    0xdb5856 to "pastel red", // -2402218
    0xfffe71 to "pastel yellow", // -399
    0xa4bf20 to "pea", // -5980384
    0x8eab12 to "pea green", // -7427310
    0x929901 to "pea soup", // -7169791
    0x94a617 to "pea soup green", // -7035369
    0xffb07c to "peach", // -20356
    0xff9a8a to "peachy pink", // -25974
    0x016795 to "peacock blue", // -16685163
    0xcbf85f to "pear", // -3409825
    0x8e82fe to "periwinkle", // -7437570
    0x8f99fb to "periwinkle blue", // -7366149
    0x8f8ce7 to "perrywinkle", // -7369497
    0x005f6a to "petrol", // -16752790
    0xe78ea5 to "pig pink", // -1601883
    0x2b5d34 to "pine", // -13935308
    0x0a481e to "pine green", // -16103394
    0xff81c0 to "pink", // -32320
    0xdb4bda to "pink purple", // -2405414
    0xf5054f to "pink red", // -719537
    0xef1de7 to "pink/purple", // -1106457
    0xd46a7e to "pinkish", // -2856322
    0xb17261 to "pinkish brown", // -5148063
    0xc8aca9 to "pinkish grey", // -3625815
    0xff724c to "pinkish orange", // -36276
    0xd648d7 to "pinkish purple", // -2733865
    0xf10c45 to "pinkish red", // -979899
    0xd99b82 to "pinkish tan", // -2516094
    0xfc86aa to "pinky", // -227670
    0xc94cbe to "pinky purple", // -3584834
    0xfc2647 to "pinky red", // -252345
    0xddd618 to "piss yellow", // -2238952
    0xc0fa8b to "pistachio", // -4130165
    0x580f41 to "plum", // -11006143
    0x4e0550 to "plum purple", // -11664048
    0x40fd14 to "poison green", // -12518124
    0x8f7303 to "poo", // -7376125
    0x885f01 to "poo brown", // -7839999
    0x7f5e00 to "poop", // -8430080
    0x7a5901 to "poop brown", // -8759039
    0x6f7c00 to "poop green", // -9470976
    0xb1d1fc to "powder blue", // -5123588
    0xffb2d0 to "powder pink", // -19760
    0x0804f9 to "primary blue", // -16251655
    0x004577 to "prussian blue", // -16759433
    0xa57e52 to "puce", // -5931438
    0xa5a502 to "puke", // -5921534
    0x947706 to "puke brown", // -7047418
    0x9aae07 to "puke green", // -6640121
    0xc2be0e to "puke yellow", // -4014578
    0xe17701 to "pumpkin", // -2001151
    0xfb7d07 to "pumpkin orange", // -295673
    0x0203e2 to "pure blue", // -16645150
    0x7e1e9c to "purple", // -8511844
    0x632de9 to "purple blue", // -10277399
    0x673a3f to "purple brown", // -10012097
    0x866f85 to "purple grey", // -7966843
    0xe03fd8 to "purple pink", // -2080808
    0x990147 to "purple red", // -6749881
    0x5d21d0 to "purple/blue", // -10673712
    0xd725de to "purple/pink", // -2677282
    0x98568d to "purpleish", // -6793587
    0x6140ef to "purpleish blue", // -10403601
    0xdf4ec8 to "purpleish pink", // -2142520
    0x8756e4 to "purpley", // -7907612
    0x5f34e7 to "purpley blue", // -10537753
    0x947e94 to "purpley grey", // -7045484
    0xc83cb9 to "purpley pink", // -3654471
    0x94568c to "purplish", // -7055732
    0x601ef9 to "purplish blue", // -10477831
    0x6b4247 to "purplish brown", // -9747897
    0x7a687f to "purplish grey", // -8755073
    0xce5dae to "purplish pink", // -3252818
    0xb0054b to "purplish red", // -5241525
    0x983fb2 to "purply", // -6799438
    0x661aee to "purply blue", // -10085650
    0xf075e6 to "purply pink", // -1018394
    0xbeae8a to "putty", // -4280694
    0x014600 to "racing green", // -16693760
    0x2cfa1f to "radioactive green", // -13829601
    0xb00149 to "raspberry", // -5242551
    0x9a6200 to "raw sienna", // -6659584
    0xa75e09 to "raw umber", // -5808631
    0xd4ffff to "really light blue", // -2818049
    0xe50000 to "red", // -1769472
    0x8b2e16 to "red brown", // -7655914
    0xfd3c06 to "red orange", // -181242
    0xfa2a55 to "red pink", // -382379
    0x820747 to "red purple", // -8255673
    0x9e0168 to "red violet", // -6422168
    0x8c0034 to "red wine", // -7602124
    0xc44240 to "reddish", // -3915200
    0x7f2b0a to "reddish brown", // -8443126
    0x997570 to "reddish grey", // -6720144
    0xf8481c to "reddish orange", // -505828
    0xfe2c54 to "reddish pink", // -119724
    0x910951 to "reddish purple", // -7272111
    0x6e1005 to "reddy brown", // -9564155
    0x021bf9 to "rich blue", // -16638983
    0x720058 to "rich purple", // -9306024
    0x8af1fe to "robin egg blue", // -7671298
    0x6dedfd to "robin's egg", // -9572867
    0x98eff9 to "robin's egg blue", // -6754311
    0xfe86a4 to "rosa", // -96604
    0xcf6275 to "rose", // -3186059
    0xf7879a to "rose pink", // -555110
    0xbe013c to "rose red", // -4325060
    0xf6688e to "rosy pink", // -628594
    0xab1239 to "rouge", // -5565895
    0x0c1793 to "royal", // -15984749
    0x0504aa to "royal blue", // -16448342
    0x4b006e to "royal purple", // -11861906
    0xca0147 to "ruby", // -3538617
    0xa13905 to "russet", // -6211323
    0xa83c09 to "rust", // -5751799
    0x8b3103 to "rust brown", // -7655165
    0xc45508 to "rust orange", // -3910392
    0xaa2704 to "rust red", // -5626108
    0xcd5909 to "rusty orange", // -3319543
    0xaf2f0d to "rusty red", // -5296371
    0xfeb209 to "saffron", // -85495
    0x87ae73 to "sage", // -7885197
    0x88b378 to "sage green", // -7818376
    0xff796c to "salmon", // -34452
    0xfe7b7c to "salmon pink", // -99460
    0xe2ca76 to "sand", // -1914250
    0xcba560 to "sand brown", // -3431072
    0xfce166 to "sand yellow", // -204442
    0xc9ae74 to "sandstone", // -3559820
    0xf1da7a to "sandy", // -927110
    0xc4a661 to "sandy brown", // -3889567
    0xfdee73 to "sandy yellow", // -135565
    0x5c8b15 to "sap green", // -10712299
    0x2138ab to "sapphire", // -14600021
    0xbe0119 to "scarlet", // -4325095
    0x3c9992 to "sea", // -12805742
    0x047495 to "sea blue", // -16485227
    0x53fca1 to "sea green", // -11273055
    0x80f9ad to "seafoam", // -8324691
    0x78d1b6 to "seafoam blue", // -8859210
    0x7af9ab to "seafoam green", // -8717909
    0x18d17b to "seaweed", // -15150725
    0x35ad6b to "seaweed green", // -13259413
    0x985e2b to "sepia", // -6791637
    0x01b44c to "shamrock", // -16665524
    0x02c14d to "shamrock green", // -16596659
    0x7f5f00 to "shit", // -8429824
    0x7b5804 to "shit brown", // -8693756
    0x758000 to "shit green", // -9076736
    0xfe02a2 to "shocking pink", // -130398
    0x9db92c to "sick green", // -6440660
    0x94b21c to "sickly green", // -7032292
    0xd0e429 to "sickly yellow", // -3087319
    0xa9561e to "sienna", // -5679586
    0xc5c9c7 to "silver", // -3814969
    0x82cafc to "sky", // -8205572
    0x75bbfd to "sky blue", // -9061379
    0x516572 to "slate", // -11442830
    0x5b7c99 to "slate blue", // -10781543
    0x658d6d to "slate green", // -10121875
    0x59656d to "slate grey", // -10918547
    0x99cc04 to "slime green", // -6697980
    0xacbb0d to "snot", // -5457139
    0x9dc100 to "snot green", // -6438656
    0x6488ea to "soft blue", // -10188566
    0x6fc276 to "soft green", // -9452938
    0xfdb0c0 to "soft pink", // -151360
    0xa66fb5 to "soft purple", // -5869643
    0x1ef876 to "spearmint", // -14747530
    0xa9f971 to "spring green", // -5637775
    0x0a5f38 to "spruce", // -16097480
    0xf2ab15 to "squash", // -873707
    0x738595 to "steel", // -9206379
    0x5a7d9a to "steel blue", // -10846822
    0x6f828a to "steel grey", // -9469302
    0xada587 to "stone", // -5397113
    0x507b9c to "stormy blue", // -11502692
    0xfcf679 to "straw", // -199047
    0xfb2943 to "strawberry", // -317117
    0x0c06f7 to "strong blue", // -15989001
    0xff0789 to "strong pink", // -63607
    0xffdf22 to "sun yellow", // -8414
    0xffc512 to "sunflower", // -15086
    0xffda03 to "sunflower yellow", // -9725
    0xfff917 to "sunny yellow", // -1769
    0xfffd37 to "sunshine yellow", // -713
    0x698339 to "swamp", // -9862343
    0x748500 to "swamp green", // -9140992
    0xd1b26f to "tan", // -3034513
    0xab7e4c to "tan brown", // -5538228
    0xa9be70 to "tan green", // -5652880
    0xff9408 to "tangerine", // -27640
    0xb9a281 to "taupe", // -4611455
    0x65ab7c to "tea", // -10114180
    0xbdf8a3 to "tea green", // -4327261
    0x029386 to "teal", // -16608378
    0x01889f to "teal blue", // -16676705
    0x25a36f to "teal green", // -14310545
    0x24bca8 to "tealish", // -14369624
    0x0cdc73 to "tealish green", // -15934349
    0xc9643b to "terra cotta", // -3578821
    0xcb6843 to "terracota", // -3446717
    0xca6641 to "terracotta", // -3512767
    0x7bf2da to "tiffany blue", // -8654118
    0xef4026 to "tomato", // -1097690
    0xec2d01 to "tomato red", // -1299199
    0x13bbaf to "topaz", // -15483985
    0xc7ac7d to "toupe", // -3691395
    0x61de2a to "toxic green", // -10363350
    0x2a7e19 to "tree green", // -13992423
    0x010fcc to "true blue", // -16707636
    0x089404 to "true green", // -16215036
    0x06c2ac to "turquoise", // -16334164
    0x06b1c4 to "turquoise blue", // -16338492
    0x04f489 to "turquoise green", // -16452471
    0x75b84f to "turtle green", // -9062321
    0x4e518b to "twilight", // -11644533
    0x0a437a to "twilight blue", // -16104582
    0x31668a to "ugly blue", // -13539702
    0x7d7103 to "ugly brown", // -8556285
    0x7a9703 to "ugly green", // -8743165
    0xcd7584 to "ugly pink", // -3312252
    0xa442a0 to "ugly purple", // -6012256
    0xd0c101 to "ugly yellow", // -3096319
    0x2000b1 to "ultramarine", // -14679887
    0x1805db to "ultramarine blue", // -15202853
    0xb26400 to "umber", // -5086208
    0x750851 to "velvet", // -9107375
    0xf4320c to "vermillion", // -773620
    0x000133 to "very dark blue", // -16776909
    0x1d0200 to "very dark brown", // -14876160
    0x062e03 to "very dark green", // -16372221
    0x2a0134 to "very dark purple", // -14024396
    0xd5ffff to "very light blue", // -2752513
    0xd3b683 to "very light brown", // -2902397
    0xd1ffbd to "very light green", // -3014723
    0xfff4f2 to "very light pink", // -2830
    0xf6cefc to "very light purple", // -602372
    0xd6fffe to "very pale blue", // -2686978
    0xcffdbc to "very pale green", // -3146308
    0x0339f8 to "vibrant blue", // -16565768
    0x0add08 to "vibrant green", // -16065272
    0xad03de to "vibrant purple", // -5438498
    0x9a0eea to "violet", // -6680854
    0x510ac9 to "violet blue", // -11466039
    0xfb5ffc to "violet pink", // -303108
    0xa50055 to "violet red", // -5963691
    0x1e9167 to "viridian", // -14773913
    0x152eff to "vivid blue", // -15388929
    0x2fef10 to "vivid green", // -13635824
    0x9900fa to "vivid purple", // -6749958
    0xa2a415 to "vomit", // -6118379
    0x89a203 to "vomit green", // -7757309
    0xc7c10c to "vomit yellow", // -3686132
    0x4b57db to "warm blue", // -11839525
    0x964e02 to "warm brown", // -6926846
    0x978a84 to "warm grey", // -6845820
    0xfb5581 to "warm pink", // -305791
    0x952e8f to "warm purple", // -7000433
    0xbcf5a6 to "washed out green", // -4393562
    0x0e87cc to "water blue", // -15824948
    0xfd4659 to "watermelon", // -178599
    0x3ae57f to "weird green", // -12917377
    0xfbdd7e to "wheat", // -270978
    0xffffff to "white", // -1
    0x3778bf to "windows blue", // -13141825
    0x80013f to "wine", // -8388289
    0x7b0323 to "wine red", // -8715485
    0x20f986 to "wintergreen", // -14616186
    0xa87dc2 to "wisteria", // -5734974
    0xffff14 to "yellow", // -236
    0xb79400 to "yellow brown", // -4746240
    0xc0fb2d to "yellow green", // -4130003
    0xcb9d06 to "yellow ochre", // -3433210
    0xfcb001 to "yellow orange", // -217087
    0xffe36e to "yellow tan", // -7314
    0xc8fd3d to "yellow/green", // -3605187
    0xbbf90f to "yellowgreen", // -4458225
    0xfaee66 to "yellowish", // -332186
    0x9b7a01 to "yellowish brown", // -6587903
    0xb0dd16 to "yellowish green", // -5186282
    0xffab0f to "yellowish orange", // -21745
    0xfcfc81 to "yellowish tan", // -197503
    0xae8b0c to "yellowy brown", // -5338356
    0xbff128 to "yellowy green", // -4198104
)
