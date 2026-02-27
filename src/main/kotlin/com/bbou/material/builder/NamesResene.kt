package com.bbou.material.builder

val RESENE_COLOR_MAP: Map<Int, String> = mapOf(
  0xff7800 to "Abbey", // -34816
  0x453626 to "Acadia", // -12241370
  0x636366 to "Acapulco", // -10263706
  0x555555 to "Acorn", // -11184811
  0x273456 to "Adriatic", // -14207914
  0xfce320 to "Alabaster", // -204000
  0xececed to "Albescent White", // -1250067
  0x111111 to "Alert Tan", // -15658735
  0x008b8b to "Alligator", // -16741493
  0x5f9ea0 to "Almond", // -10510688
  0x504749 to "Alpine", // -11516087
  0x303030 to "Aluminium", // -13619152
  0xe90387 to "Amaranth", // -1506425
  0x342c1d to "Amazon", // -13358051
  0xc2c2a7 to "Amber", // -4013401
  0x464646 to "Americano", // -12171706
  0x666666 to "Amethyst", // -10066330
  0x2c2c2d to "Anakiwa", // -13882323
  0x292929 to "Ancestral", // -14079703
  0xefeff0 to "Apricot", // -1052688
  0x008000 to "Aquamarine", // -16744448
  0x484849 to "Arrowtown", // -12040119
  0x525253 to "Ash", // -11382189
  0x222223 to "Aspalt", // -14540253
  0x000000 to "Atomic", // -16777216
  0x3c3c3c to "Auckland", // -12829636
  0x555554 to "Avocado", // -11184812
  0x474748 to "Axolotl", // -12105912
  0xdedee0 to "Aztec", // -2171168
  0x636365 to "Bahia", // -10263707
  0x273455 to "Bali Hai", // -14207915
  0xececec to "Barley White", // -1250068
  0x111110 to "Barossa", // -15658736
  0x008b8a to "Bastille", // -16741494
  0x5f9e9f to "Battle Grey", // -10510689
  0x464645 to "Bay Leaf", // -12171707
  0x555553 to "Bazaar", // -11184813
  0x30302f to "Bermuda", // -13619153
  0xe90386 to "Biscay", // -1506426
  0x342c1c to "Bison Hide", // -13358052
  0xc2c2a6 to "Bitter Lemon", // -4013402
  0x2c2c2c to "Black", // -13882324
  0x292928 to "Black Forest", // -14079704
  0x3c3c3b to "Black Marlin", // -12829637
  0xefefef to "Black Pearl", // -1052689
  0x007fff to "Black Rock", // -16744449
  0x484848 to "Black Rose", // -12040120
  0x525252 to "Black White", // -11382190
  0x222222 to "Blackcurrant", // -14540254
  0x555552 to "Blue Charcoal", // -11184814
  0x474747 to "Blue Night", // -12105913
  0xdededf to "Blue Smoke", // -2171169
  0x636364 to "Blue Whale", // -10263708
  0x273454 to "Blue Zodiac", // -14207916
  0xececeb to "Blumine", // -1250069
  0x11110f to "Bokara Grey", // -15658737
  0x008b89 to "Bordeaux", // -16741495
  0x5f9e9e to "Bottle Green", // -10510690
  0x464644 to "Boulder", // -12171708
  0x555551 to "Bracken", // -11184815
  0x30302e to "Brandy", // -13619154
  0xe90385 to "Bright Red", // -1506427
  0x342c1b to "Brite Grey", // -13358053
  0xc2c2a5 to "Bronze", // -4013403
  0x2c2c2b to "Brown Bramble", // -13882325
  0x292927 to "Brown Derby", // -14079705
  0x3c3c3a to "Brown Rust", // -12829638
  0xefefee to "Bull Shot", // -1052690
  0x007ffe to "Bunker", // -16744450
  0x484847 to "Burgundy", // -12040121
  0x525251 to "Burnham", // -11382191
  0x222221 to "Burning Sand", // -14540255
  0x555550 to "Bush", // -11184816
  0x474746 to "Buttercup", // -12105914
  0xdedede to "Cabbage Pont", // -2171170
  0x636363 to "Cadillac", // -10263709
  0x273453 to "Café Royale", // -14207917
  0xececea to "Calico", // -1250070
  0x11110e to "California", // -15658738
  0x008b88 to "Calypso", // -16741496
  0x5f9e9d to "Camelot", // -10510691
  0x464643 to "Can Can", // -12171709
  0x55554f to "Cannon Pink", // -11184817
  0x30302d to "Cape Cod", // -13619155
  0xe90384 to "Cape Honey", // -1506428
  0x342c1a to "Cape Palliser", // -13358054
  0xc2c2a4 to "Caramel", // -4013404
  0x2c2c2a to "Cararra", // -13882326
  0x292926 to "Cardin Green", // -14079706
  0x3c3c39 to "Cargo", // -12829639
  0xefefed to "Carnaby Tan", // -1052691
  0x007ffd to "Casablanca", // -16744451
  0x484846 to "Casal", // -12040122
  0x525250 to "Cascade", // -11382192
  0x222220 to "Cashmere", // -14540256
  0x55554e to "Casper", // -11184818
  0x474745 to "Castle Rock", // -12105915
  0xdededd to "Catskill White", // -2171171
  0x636362 to "Cavern Pink", // -10263710
  0x273452 to "Cedar", // -14207918
  0xecece9 to "Celery", // -1250071
  0x11110d to "Celeste", // -15658739
  0x008b87 to "Cello", // -16741497
  0x5f9e9c to "Ceramic", // -10510692
  0x464642 to "Chalet Green", // -12171710
  0x55554d to "Chalky", // -11184819
  0x30302c to "Chambray", // -13619156
  0xe90383 to "Chamoisee", // -1506429
  0x342c19 to "Champagne", // -13358055
  0xc2c2a3 to "Chantilly", // -4013405
  0x2c2c29 to "Charade", // -13882327
  0x292925 to "Chardon", // -14079707
  0x3c3c38 to "Chardonnay", // -12829640
  0xefefec to "Charleston Green", // -1052692
  0x007ffc to "Charlotte", // -16744452
  0x484845 to "Chartreuse", // -12040123
  0x52524f to "Chateau", // -11382193
  0x22221f to "Chateau Green", // -14540257
  0x55554c to "Chelsea Gem", // -11184820
  0x474744 to "Chenin", // -12105916
  0xdededc to "Cherub", // -2171172
  0x636361 to "Chetwode Blue", // -10263711
  0x273451 to "Chicago", // -14207919
  0xecece8 to "Chiffon", // -1250072
  0x11110c to "Chilean Heath", // -15658740
  0x008b86 to "China Ivory", // -16741498
  0x5f9e9b to "Chino", // -10510693
  0x464641 to "Chinook", // -12171711
  0x55554b to "Clairvoyant", // -11184821
  0x30302b to "Clam Shell", // -13619157
  0xe90382 to "Claret", // -1506430
  0x342c18 to "Classico", // -13358056
  0xc2c2a2 to "Clay Creek", // -4013406
  0x2c2c28 to "Clear Day", // -13882328
  0x292924 to "Clinker", // -14079708
  0x3c3c37 to "Cloud", // -12829641
  0xefefeb to "Cloud Burst", // -1052693
  0x007ffb to "Clover", // -16744453
  0x484844 to "Cobalt", // -12040124
  0x52524e to "Coconut Cream", // -11382194
  0x22221e to "Cod Grey", // -14540258
  0x55554a to "Coffee Bean", // -11184822
  0x474743 to "Cognac", // -12105917
  0xdededb to "Cola", // -2171173
  0x636360 to "Cold Turkey", // -10263712
  0x273450 to "Colonial White", // -14207920
  0xecece7 to "Comet", // -1250073
  0x11110b to "Concord", // -15658741
  0x008b85 to "Concrete", // -16741499
  0x5f9e9a to "Confetti", // -10510694
  0x464640 to "Conifer", // -12171712
  0x555549 to "Contessa", // -11184823
  0x30302a to "Copper", // -13619158
  0xe90381 to "Copper Canyon", // -1506431
  0x342c17 to "Copper Rose", // -13358057
  0xc2c2a1 to "Coquina", // -4013407
  0x2c2c27 to "Coral Reef", // -13882329
  0x292923 to "Corduroy", // -14079709
  0x3c3c36 to "Coriander", // -12829642
  0xefefea to "Cork", // -1052694
  0x007ffa to "Corn Field", // -16744454
  0x484843 to "Cornflower", // -12040125
  0x52524d to "Cornsilk", // -11382195
  0x22221d to "Corvette", // -14540259
  0x555548 to "Cosmic", // -11184824
  0x474742 to "Cosmos", // -12105918
  0xdededa to "Costa Del Sol", // -2171174
  0x63635f to "Cotton Seed", // -10263713
  0x27344f to "County Green", // -14207921
  0xecece6 to "Cowboy", // -1250074
  0x11110a to "Crail", // -15658742
  0x008b84 to "Cranberry", // -16741500
  0x5f9e99 to "Crater Brown", // -10510695
  0x46463f to "Cream", // -12171713
  0x555547 to "Creole", // -11184825
  0x303029 to "Crete", // -13619159
  0xe90380 to "Crimson", // -1506432
  0x342c16 to "Crocodile", // -13358058
  0xc2c2a0 to "Crown of Thorns", // -4013408
  0x2c2c26 to "Crude Oil", // -13882330
  0x292922 to "Cruise", // -14079710
  0x3c3c35 to "Crusoe", // -12829643
  0xefefe9 to "Crystal", // -1052695
  0x007ff9 to "Cuarzo", // -16744455
  0x484842 to "Cumin", // -12040126
  0x52524c to "Cumulus", // -11382196
  0x22221c to "Cyan", // -14540260
  0x555546 to "Cyprus", // -11184826
  0xefefe8 to "Daffron", // -1052696
  0x007ff8 to "Dairy Cream", // -16744456
  0x484841 to "Daisy Bush", // -12040127
  0x52524b to "Dallas", // -11382197
  0x22221b to "Dandelion", // -14540261
  0x555545 to "Danube", // -11184827
  0x474741 to "Dark Ebony", // -12105919
  0xdeded9 to "Dark Fern", // -2171175
  0x63635e to "Dashel", // -10263714
  0x27344e to "Davys Grey", // -14207922
  0xecece5 to "Dawn", // -1250075
  0x111109 to "Dawn Pink", // -15658743
  0x008b83 to "Deep Bronze", // -16741501
  0x5f9e98 to "Deep Cove", // -10510696
  0x46463e to "Deep Fir", // -12171714
  0x555544 to "Deep Koamaru", // -11184828
  0x303028 to "Deep Oak", // -13619160
  0xe9037f to "Deep Ocean", // -1506433
  0x342c15 to "Deep Sapphire", // -13358059
  0xc2c29f to "Deep Teal", // -4013409
  0x2c2c25 to "Del Rio", // -13882331
  0x292921 to "Dell", // -14079711
  0x3c3c34 to "Delta", // -12829644
  0xefefe7 to "Deluge", // -1052697
  0x007ff7 to "Derby", // -16744457
  0x484840 to "Desert", // -12040128
  0x52524a to "Desert Sand", // -11382198
  0x22221a to "Dew", // -14540262
  0x555543 to "Diesel", // -11184829
  0x474740 to "Dingley", // -12105920
  0xdeded8 to "Disco", // -2171176
  0x63635d to "Discovery", // -10263715
  0x27344d to "Dixie", // -14207923
  0xecece4 to "Dolly", // -1250076
  0x111108 to "Don Juan", // -15658744
  0x008b82 to "Donegal", // -16741502
  0x5f9e97 to "Dorian", // -10510697
  0x46463d to "Doubtless Bay", // -12171715
  0x555542 to "Dove Grey", // -11184830
  0x303027 to "Downy", // -13619161
  0xe9037e to "Driftwood", // -1506434
  0x342c14 to "Drover", // -13358060
  0xc2c29e to "Dune", // -4013410
  0x2c2c24 to "Dusty Grey", // -13882332
  0x292920 to "Eagle", // -14079712
  0x3c3c33 to "Earls Green", // -12829645
  0xefefe6 to "Early Dawn", // -1052698
  0x007ff6 to "East Bay", // -16744458
  0x48483f to "East Side", // -12040129
  0x525249 to "Ebony", // -11382199
  0x222219 to "Ebony Clay", // -14540263
  0x555541 to "Eclipse", // -11184831
  0x47473f to "Ecru White", // -12105921
  0xdeded7 to "Eden", // -2171177
  0x63635c to "Edgewater", // -10263716
  0x27344c to "Edward", // -14207924
  0xecece3 to "Egg Sour", // -1250077
  0x111107 to "Egg White", // -15658745
  0x008b81 to "Eggplant", // -16741503
  0x5f9e96 to "El Paso", // -10510698
  0x46463c to "El Salva", // -12171716
  0x555540 to "Elephant", // -11184832
  0x303026 to "Elf Green", // -13619162
  0xe9037d to "Elm", // -1506435
  0x342c13 to "Emerald", // -13358061
  0xc2c29d to "Eminence", // -4013411
  0x2c2c23 to "Emperor", // -13882333
  0x29291f to "Empress", // -14079713
  0x3c3c32 to "Endeavour", // -12829646
  0xefefe5 to "Energy Yellow", // -1052699
  0x007ff5 to "English Holly", // -16744459
  0x48483e to "English Walnut", // -12040130
  0x525248 to "Envy", // -11382200
  0x222218 to "Equator", // -14540264
  0x55553f to "Eskimo", // -11184833
  0x47473e to "Espresso", // -12105922
  0xdeded6 to "Eternity", // -2171178
  0x63635b to "Eucalyptus", // -10263717
  0x27344b to "Eureka", // -14207925
  0xecece2 to "Everglade", // -1250078
  0x111106 to "Evolution", // -15658746
  0x008b80 to "Falcon", // -16741504
  0x5f9e95 to "Fall Green", // -10510699
  0x46463b to "Fantasy", // -12171717
  0x55553e to "Fedora", // -11184834
  0x303025 to "Feijoa", // -13619163
  0xe9037c to "Feldspar", // -1506436
  0x342c12 to "Fern", // -13358062
  0xc2c29c to "Fern Frond", // -4013412
  0x2c2c22 to "Feta", // -13882334
  0x29291e to "Fiery Orange", // -14079714
  0x3c3c31 to "Fiji Green", // -12829647
  0xefefe4 to "Finch", // -1052700
  0x007ff4 to "Finlandia", // -16744460
  0x48483d to "Finn", // -12040131
  0x525247 to "Fiord", // -11382201
  0x222217 to "Fire", // -14540265
  0x55553d to "Fire Bush", // -11184835
  0x47473d to "Firefly", // -12105923
  0xdeded5 to "Flame", // -2171179
  0x63635a to "Flamenco", // -10263718
  0x27344a to "Flamingo", // -14207926
  0xecece1 to "Flax", // -1250079
  0x111105 to "Flint", // -15658747
  0x008b7f to "Flotsam", // -16741505
  0x5f9e94 to "Flower Power", // -10510700
  0x46463a to "Flush Mahogany", // -12171718
  0x55553c to "Foam", // -11184836
  0x303024 to "Fog", // -13619164
  0xe9037b to "Foggy Grey", // -1506437
  0x342c11 to "Forest Green", // -13358063
  0xc2c29b to "Forget Me Not", // -4013413
  0x2c2c21 to "Fountain Blue", // -13882335
  0x29291d to "Frangipani", // -14079715
  0x3c3c30 to "French Grey", // -12829648
  0xefefe3 to "French Pass", // -1052701
  0x007ff3 to "French Rose", // -16744461
  0x48483c to "Fresh Eggplant", // -12040132
  0x525246 to "Friar Grey", // -11382202
  0x222216 to "Fringy Flower", // -14540266
  0x55553b to "Fuego", // -11184837
  0x47473c to "Fuel Town", // -12105924
  0xdeded4 to "Fun Blue", // -2171180
  0x636359 to "Fuscous Grey", // -10263719
  0x273449 to "Gable Green", // -14207927
  0xecece0 to "Gainsboro", // -1250080
  0x008b7e to "Galliano", // -16741506
  0x111104 to "Gallo", // -15658748
  0x5f9e93 to "Gamboge", // -10510701
  0x464639 to "Geyser", // -12171719
  0x55553a to "Ghost", // -11184838
  0x303023 to "Giggle", // -13619165
  0xe9037a to "Gin", // -1506438
  0x342c10 to "Gin Fizz", // -13358064
  0xc2c29a to "Givry", // -4013414
  0x2c2c20 to "Glade Green", // -13882336
  0x29291c to "Go Ben", // -14079716
  0x3c3c2f to "Goblin", // -12829649
  0xefefe2 to "Gold", // -1052702
  0x007ff2 to "Gold Drop", // -16744462
  0x48483b to "Gold Tips", // -12040133
  0x525245 to "Golden Bell", // -11382203
  0x222215 to "Golden Dream", // -14540267
  0x555539 to "Golden Fizz", // -11184839
  0x47473b to "Golden Glow", // -12105925
  0xdeded3 to "Golden Grass", // -2171181
  0x636358 to "Golden Sand", // -10263720
  0x273448 to "Golden Tainoi", // -14207928
  0xececdf to "Goldenrod", // -1250081
  0x111103 to "Gondola", // -15658749
  0x008b7d to "Gordons Green", // -16741507
  0x5f9e92 to "Gorse", // -10510702
  0x464638 to "Gossamer", // -12171720
  0x555538 to "Gossip", // -11184840
  0x303022 to "Gothic", // -13619166
  0xe90379 to "Governor Bay", // -1506439
  0x342c0f to "Grain Brown", // -13358065
  0xc2c299 to "Grandis", // -4013415
  0x2c2c1f to "Granite Green", // -13882337
  0x29291b to "Granny Apple", // -14079717
  0x3c3c2e to "Granny Smith", // -12829650
  0xefefe1 to "Grape", // -1052703
  0x007ff1 to "Graphite", // -16744463
  0x48483a to "Gravel", // -12040134
  0x525244 to "Green", // -11382204
  0x222214 to "Green House", // -14540268
  0x555537 to "Green Kelp", // -11184841
  0x47473a to "Green Leaf", // -12105926
  0xdeded2 to "Green Mist", // -2171182
  0x636357 to "Green Pea", // -10263721
  0x273447 to "Green Smoke", // -14207929
  0xececde to "Green Spring", // -1250082
  0x111102 to "Green Vogue", // -15658750
  0x008b7c to "Green Waterman", // -16741508
  0x5f9e91 to "Green White", // -10510703
  0x464637 to "Green Willow", // -12171721
  0x555536 to "Greenhouse", // -11184842
  0x303021 to "Grenadier", // -13619167
  0xe90378 to "Grey", // -1506440
  0x342c0e to "Grey Chateau", // -13358066
  0xc2c298 to "Grey Goose", // -4013416
  0x2c2c1e to "Grey Nickel", // -13882338
  0x29291a to "Grey Nurse", // -14079718
  0x3c3c2d to "Grey Olive", // -12829651
  0xefefe0 to "Grizzly", // -1052704
  0x007ff0 to "Guardsman Red", // -16744464
  0x484839 to "Gulf Blue", // -12040135
  0x525243 to "Gull Grey", // -11382205
  0x222213 to "Gum Leaf", // -14540269
  0x555535 to "Gumbo", // -11184843
  0x474739 to "Gun Powder", // -12105927
  0xdeded1 to "Gunsmoke", // -2171183
  0x636356 to "Gurkha", // -10263722
  0x273446 to "Hacienda", // -14207930
  0xececdd to "Hairy Heath", // -1250083
  0x111101 to "Haiti", // -15658751
  0x008b7b to "Half Baked", // -16741509
  0x5f9e90 to "Half Colonial White", // -10510704
  0x464636 to "Half Dutch White", // -12171722
  0x555534 to "Half Spanish White", // -11184844
  0x303020 to "Half and Half", // -13619168
  0xe90377 to "Hampton", // -1506441
  0x342c0d to "Harlequin", // -13358067
  0xc2c297 to "Harp", // -4013417
  0x2c2c1d to "Harvest Gold", // -13882339
  0x292919 to "Havelock Blue", // -14079719
  0x3c3c2c to "Hawaiian Tan", // -12829652
  0xefefdf to "Hawkes Blue", // -1052705
  0x007fef to "Heath", // -16744465
  0x484838 to "Heather", // -12040136
  0x525242 to "Heathered Grey", // -11382206
  0x222212 to "Heavy Metal", // -14540270
  0x555533 to "Heliotrope", // -11184845
  0x474738 to "Hemlock", // -12105928
  0xdeded0 to "Hemp", // -2171184
  0x636355 to "Hibiscus", // -10263723
  0x273445 to "High Highland", // -14207931
  0xececdc to "Hillary", // -1250084
  0x111100 to "Himalaya", // -15658752
  0x008b7a to "Hint of Grey", // -16741510
  0x5f9e8f to "Hint of Red", // -10510705
  0x464635 to "Hint of Yellow", // -12171723
  0x555532 to "Hippy Blue", // -11184846
  0x30301f to "Hippy Green", // -13619169
  0xe90376 to "Hokey Pokey", // -1506442
  0x342c0c to "Hoki", // -13358068
  0xc2c296 to "Holly", // -4013418
  0x2c2c1c to "Honey Flower", // -13882340
  0x292918 to "Honeysuckle", // -14079720
  0x3c3c2b to "Hopbush", // -12829653
  0xefefde to "Horizon", // -1052706
  0x007fee to "Horses", // -16744466
  0x484837 to "Hot Chile", // -12040137
  0x525241 to "Hot Pink", // -11382207
  0x222211 to "Hot Toddy", // -14540271
  0x555531 to "Hummus", // -11184847
  0x474737 to "Hurricane", // -12105929
  0xdedecf to "Husk", // -2171185
  0x474736 to "Iceberg", // -12105930
  0xdedece to "Iko Iko", // -2171186
  0x636354 to "Illusion", // -10263724
  0x273444 to "Imprint", // -14207932
  0xececdb to "Indian Tan", // -1250085
  0x1110ff to "Indigo", // -15658753
  0x008b79 to "Indochine", // -16741511
  0x5f9e8e to "Inkree", // -10510706
  0x464634 to "Innuendo", // -12171724
  0x555530 to "Inside Back", // -11184848
  0x30301e to "Irish Coffee", // -13619170
  0xe90375 to "Ironside Grey", // -1506443
  0x342c0b to "Ironstone", // -13358069
  0xc2c295 to "Island Spice", // -4013419
  0x2c2c1b to "Ivory", // -13882341
  0x292917 to "Jacaranda", // -14079721
  0x3c3c2a to "Jacky", // -12829654
  0xefefdd to "Jade", // -1052707
  0x007fed to "Jagger", // -16744467
  0x484836 to "Jambalaya", // -12040138
  0x525240 to "Janna", // -11382208
  0x222210 to "Japanese Laurel", // -14540272
  0x55552f to "Japonica", // -11184849
  0x474735 to "Java", // -12105931
  0xdedecd to "Jelly Bean", // -2171187
  0x636353 to "Jet Stream", // -10263725
  0x273443 to "Jewel", // -14207933
  0xececda to "Jitney", // -1250086
  0x1110fe to "Jordan", // -15658754
  0x008b78 to "Judge Grey", // -16741512
  0x5f9e8d to "Jumbo", // -10510707
  0x464633 to "Jungle Green", // -12171725
  0x55552e to "Jungle Mist", // -11184850
  0x30301d to "Juniper", // -13619171
  0xe90374 to "Just Right", // -1506444
  0x342c0a to "Kabul", // -13358070
  0xc2c294 to "Kaitoke Green", // -4013420
  0x2c2c1a to "Kangaroo", // -13882342
  0x292916 to "Karaka", // -14079722
  0x3c3c29 to "Karry", // -12829655
  0xefefdc to "Kashmir Blue", // -1052708
  0x007fec to "Kelp", // -16744468
  0x484835 to "Keppel", // -12040139
  0x52523f to "Key Lime Pie", // -11382209
  0x22220f to "Khaki", // -14540273
  0x55552d to "Kidnapper", // -11184851
  0x474734 to "Killarney", // -12105932
  0xdedecc to "Kimberly", // -2171188
  0x636352 to "Kingfisher Daisy", // -10263726
  0x273442 to "Kobi", // -14207934
  0xececd9 to "Kobiene", // -1250087
  0x1110fd to "Kokoda", // -15658755
  0x008b77 to "Koranui", // -16741513
  0x5f9e8c to "Koromiko", // -10510708
  0x464632 to "Kumutoto", // -12171726
  0x55552c to "Kurous", // -11184852
  0x30301c to "La Palma", // -13619172
  0xe90373 to "La Rioja", // -1506445
  0x342c09 to "Las Palmas", // -13358071
  0xc2c293 to "Lascars", // -4013421
  0x2c2c19 to "Lava", // -13882343
  0x292915 to "Lavender", // -14079723
  0x3c3c28 to "Lavender Grey", // -12829656
  0xefefdb to "Leather", // -1052709
  0x007feb to "Lemon", // -16744469
  0x484834 to "Lemon Grass", // -12040140
  0x52523e to "Lichen", // -11382210
  0x22220e to "Lightning Yellow", // -14540274
  0x55552b to "Lilac", // -11184853
  0x474733 to "Lilac Bush", // -12105933
  0xdedecb to "Lily", // -2171189
  0x636351 to "Lily White", // -10263727
  0x273441 to "Lima", // -14207935
  0xececd8 to "Limeade", // -1250088
  0x1110fc to "Limerick", // -15658756
  0x008b76 to "Linen", // -16741514
  0x5f9e8b to "Linseed", // -10510709
  0x464631 to "Loblolly", // -12171727
  0x55552a to "Lochinvar", // -11184854
  0x30301b to "Lochmara", // -13619173
  0xe90372 to "Locust", // -1506446
  0x342c08 to "Log Cabin", // -13358072
  0xc2c292 to "Logan", // -4013422
  0x2c2c18 to "Lola", // -13882344
  0x292914 to "London Hue", // -14079724
  0x3c3c27 to "Loner", // -12829657
  0xefefda to "Lotus", // -1052710
  0x007fea to "Loulou", // -16744470
  0x484833 to "Lucky", // -12040141
  0x52523d to "Lucky Point", // -11382211
  0x22220d to "Lunar Green", // -14540275
  0x555529 to "Lusty", // -11184855
  0x474732 to "Luxor Gold", // -12105934
  0xdedeca to "Lynch", // -2171190
  0x636350 to "Mabel", // -10263728
  0x273440 to "Macaroni and Cheese", // -14207936
  0xececd7 to "Madang", // -1250089
  0x1110fb to "Madras", // -15658757
  0x008b75 to "Magenta", // -16741515
  0x5f9e8a to "Magnolia", // -10510710
  0x464630 to "Mahogany", // -12171728
  0x555528 to "Mai Tai", // -11184856
  0x30301a to "Maize", // -13619174
  0xe90371 to "Makara", // -1506447
  0x342c07 to "Mako", // -13358073
  0xc2c291 to "Malachite", // -4013423
  0x2c2c17 to "Malibu", // -13882345
  0x292913 to "Maltese", // -14079725
  0x3c3c26 to "Mamba", // -12829658
  0xefefd9 to "Manatee", // -1052711
  0x007fe9 to "Mandalay", // -16744471
  0x484832 to "Mandarin", // -12040142
  0x52523c to "Mandy", // -11382212
  0x22220c to "Manhattan", // -14540276
  0x555527 to "Mantal", // -11184857
  0x474731 to "Mantis", // -12105935
  0xdedec9 to "Manz", // -2171191
  0x63634f to "Mardi Gras", // -10263729
  0x27343f to "Marigold", // -14207937
  0xececd6 to "Mariner", // -1250090
  0x1110fa to "Maroon", // -15658758
  0x008b74 to "Marshland", // -16741516
  0x5f9e89 to "Martini", // -10510711
  0x46462f to "Martinique", // -12171729
  0x555526 to "Marzipan", // -11184858
  0x303019 to "Masala", // -13619175
  0xe90370 to "Matisse", // -1506448
  0x342c06 to "Matrix", // -13358074
  0xc2c290 to "Matterhorn", // -4013424
  0x2c2c16 to "Maverick", // -13882346
  0x292912 to "Melanie", // -14079726
  0x3c3c25 to "Melon", // -12829659
  0xefefd8 to "Melrose", // -1052712
  0x007fe8 to "Mercury", // -16744472
  0x484831 to "Merino", // -12040143
  0x52523b to "Merlin", // -11382213
  0x22220b to "Merlot", // -14540277
  0x555525 to "Metallic Bronze", // -11184859
  0x474730 to "Meteor", // -12105936
  0xdedec8 to "Mexican Red", // -2171192
  0x63634e to "Mid Grey", // -10263730
  0x27343e to "Midnight", // -14207938
  0xececd5 to "Midnight Blue", // -1250091
  0x1110f9 to "Midnight Moss", // -15658759
  0x008b73 to "Mikado", // -16741517
  0x5f9e88 to "Milan", // -10510712
  0x46462e to "Milano Red", // -12171730
  0x555524 to "Milk Punch", // -11184860
  0x303018 to "Millbrook", // -13619176
  0xe9036f to "Mimosa", // -1506449
  0x342c05 to "Mindaro", // -13358075
  0xc2c28f to "Mine Shaft", // -4013425
  0x2c2c15 to "Mineral Green", // -13882347
  0x292911 to "Mint", // -14079727
  0x3c3c24 to "Mint Julep", // -12829660
  0xefefd7 to "Mint Tulip", // -1052713
  0x007fe7 to "Mirage", // -16744473
  0x484830 to "Mischief", // -12040144
  0x52523a to "Mist Grey", // -11382214
  0x22220a to "Mistletoe", // -14540278
  0x555523 to "Mobster", // -11184861
  0x47472f to "Moccaccino", // -12105937
  0xdedec7 to "Mocha", // -2171193
  0x63634d to "Mojo", // -10263731
  0x27343d to "Mona Lisa", // -14207939
  0xececd4 to "Monarch", // -1250092
  0x1110f8 to "Mondrian", // -15658760
  0x008b72 to "Mongoose", // -16741518
  0x5f9e87 to "Monza", // -10510713
  0x46462d to "Moon Mist", // -12171731
  0x555522 to "Moon Rakir", // -11184862
  0x303017 to "Moonbeam", // -13619177
  0xe9036e to "Moonlight", // -1506450
  0x342c04 to "Morning Glory", // -13358076
  0xc2c28e to "Morocco", // -4013426
  0x2c2c14 to "Mortar", // -13882348
  0x292910 to "Mosque", // -14079728
  0x3c3c23 to "Moss Green", // -12829661
  0xefefd6 to "Mountain Mist", // -1052714
  0x007fe6 to "Muddy Waters", // -16744474
  0x48482f to "Muesli", // -12040145
  0x525239 to "Mulberry", // -11382215
  0x222209 to "Mule Fawn", // -14540279
  0x555521 to "Mullingar", // -11184863
  0x47472e to "Mustard", // -12105938
  0xdedec6 to "My Pink", // -2171194
  0x63634c to "Myrtle", // -10263732
  0x27343c to "Mystic", // -14207940
  0x47472d to "Nandor", // -12105939
  0xdedec5 to "Napa", // -2171195
  0x63634b to "Narvik", // -10263733
  0x27343b to "Nebula", // -14207941
  0xececd3 to "Negroni", // -1250093
  0x1110f7 to "Nepal", // -15658761
  0x008b71 to "Neptune", // -16741519
  0x5f9e86 to "Nero", // -10510714
  0x46462c to "Nevada", // -12171732
  0x555520 to "New Amber", // -11184864
  0x303016 to "New York Pink", // -13619178
  0xe9036d to "Niagara", // -1506451
  0x342c03 to "Night Rider", // -13358077
  0xc2c28d to "Night Shadz", // -4013427
  0x2c2c13 to "Nile Blue", // -13882349
  0x29290f to "Nobel", // -14079729
  0x3c3c22 to "Nomad", // -12829662
  0xefefd5 to "Norway", // -1052715
  0x007fe5 to "Nugget", // -16744475
  0x48482e to "Nutmeg", // -12040146
  0x525238 to "Oak", // -11382216
  0x222208 to "Oasis", // -14540280
  0x55551f to "Observatory", // -11184865
  0x47472c to "Ocean Boat Blue", // -12105940
  0xdedec4 to "Ochre", // -2171196
  0x63634a to "Off Green", // -10263734
  0x27343a to "Off Yellow", // -14207942
  0xececd2 to "Oil", // -1250094
  0x1110f6 to "Old Brick", // -15658762
  0x008b70 to "Old Copper", // -16741520
  0x5f9e85 to "Old Gold", // -10510715
  0x46462b to "Old Lace", // -12171733
  0x55551e to "Old Lavender", // -11184866
  0x303015 to "Old Rose", // -13619179
  0xe9036c to "Olive", // -1506452
  0x342c02 to "Olive Drab", // -13358078
  0xc2c28c to "Olive Green", // -4013428
  0x2c2c12 to "Olive Haze", // -13882350
  0x29290e to "Olivetone", // -14079730
  0x3c3c21 to "Olivine", // -12829663
  0xefefd4 to "Onahau", // -1052716
  0x007fe4 to "Onion", // -16744476
  0x48482d to "Opal", // -12040147
  0x525237 to "Opium", // -11382217
  0x222207 to "Oracle", // -14540281
  0x55551d to "Orange", // -11184867
  0x47472b to "Orange Roughy", // -12105941
  0xdedec3 to "Orange White", // -2171197
  0x636349 to "Orchid", // -10263735
  0x273439 to "Oregon", // -14207943
  0xececd1 to "Orient", // -1250095
  0x1110f5 to "Oriental Pink", // -15658763
  0x008b6f to "Orinoco", // -16741521
  0x5f9e84 to "Oslo Grey", // -10510716
  0x46462a to "Ottoman", // -12171734
  0x55551c to "Outer Space", // -11184868
  0x303014 to "Outrageous Orange", // -13619180
  0xe9036b to "Oxford Blue", // -1506453
  0x342c01 to "Oxley", // -13358079
  0xc2c28b to "Oyster Bay", // -4013429
  0x2c2c11 to "Oyster Pink", // -13882351
  0x29290d to "Paarl", // -14079731
  0x3c3c20 to "Pablo", // -12829664
  0xefefd3 to "Pacific Blue", // -1052717
  0x007fe3 to "Paddywack", // -16744477
  0x48482c to "Pampas", // -12040148
  0x525236 to "Panache", // -11382218
  0x222206 to "Pancho", // -14540282
  0x55551b to "Pansy Purple", // -11184869
  0x47472a to "Paoli", // -12105942
  0xdedec2 to "Papaya Whip", // -2171198
  0x636348 to "Paprika", // -10263736
  0x273438 to "Paradiso", // -14207944
  0xececd0 to "Parchment", // -1250096
  0x1110f4 to "Paris Daisy", // -15658764
  0x008b6e to "Paris M", // -16741522
  0x5f9e83 to "Parsley", // -10510717
  0x464629 to "Pastel Green", // -12171735
  0x55551a to "Pastel Pink", // -11184870
  0x303013 to "Patina", // -13619181
  0xe9036a to "Pattens Blue", // -1506454
  0x342c00 to "Paua", // -13358080
  0xc2c28a to "Pavlova", // -4013430
  0x2c2c10 to "Peach", // -13882352
  0x29290c to "Peach Orange", // -14079732
  0x3c3c1f to "Peach Puff", // -12829665
  0xefefd2 to "Peanut", // -1052718
  0x007fe2 to "Pear", // -16744478
  0x48482b to "Pearl Bush", // -12040149
  0x525235 to "Pearl Lusta", // -11382219
  0x222205 to "Peat", // -14540283
  0x555519 to "Pelorous", // -11184871
  0x474729 to "Peppermint", // -12105943
  0xdedec1 to "Perano", // -2171199
  0x636347 to "Perfume", // -10263737
  0x273437 to "Periglacial Blue", // -14207945
  0xececcf to "Periwinkle", // -1250097
  0x1110f3 to "Periwinkle Grey", // -15658765
  0x008b6d to "Persian Rose", // -16741523
  0x5f9e82 to "Persimmon", // -10510718
  0x464628 to "Peru Tan", // -12171736
  0x555518 to "Pesto", // -11184872
  0x303012 to "Petite Orchid", // -13619182
  0xe90369 to "Pewter", // -1506455
  0x342bff to "Pharlap", // -13358081
  0xc2c289 to "Picasso", // -4013431
  0x2c2c0f to "Pickled Bean", // -13882353
  0x29290b to "Pickled Bluewood", // -14079733
  0x3c3c1e to "Picton Blue", // -12829666
  0xefefd1 to "Pig Pink", // -1052719
  0x007fe1 to "Pigeon Post", // -16744479
  0x48482a to "Pigment Indigo", // -12040150
  0x525234 to "Pine Cone", // -11382220
  0x222204 to "Pine Glade", // -14540284
  0x555517 to "Pine Green", // -11184873
  0x474728 to "Pine Tree", // -12105944
  0xdedec0 to "Pink", // -2171200
  0x636346 to "Pink Flamingo", // -10263738
  0x273436 to "Pink Lace", // -14207946
  0xececce to "Pink Lady", // -1250098
  0x1110f2 to "Pink Swan", // -15658766
  0x008b6c to "Piper", // -16741524
  0x5f9e81 to "Pipi", // -10510719
  0x464627 to "Piquance", // -12171737
  0x555516 to "Pirate Gold", // -11184874
  0x303011 to "Pistachio", // -13619183
  0xe90368 to "Pixie", // -1506456
  0x342bfe to "Plum", // -13358082
  0xc2c288 to "Pohutukawa", // -4013432
  0x2c2c0e to "Polar", // -13882354
  0x29290a to "Polo Blue", // -14079734
  0x3c3c1d to "Pomegranate", // -12829667
  0xefefd0 to "Pompadour", // -1052720
  0x007fe0 to "Porcelain", // -16744480
  0x484829 to "Poro", // -12040151
  0x525233 to "Port Gore", // -11382221
  0x222203 to "Portafino", // -14540285
  0x555515 to "Portage", // -11184875
  0x474727 to "Portica", // -12105945
  0xdedebf to "Pot Pourri", // -2171201
  0x636345 to "Potters Clay", // -10263739
  0x273435 to "Powder Blue", // -14207947
  0xececcd to "Prairie Sand", // -1250099
  0x1110f1 to "Prelude", // -15658767
  0x008b6b to "Primrose", // -16741525
  0x5f9e80 to "Promenade", // -10510720
  0x464626 to "Pueblo", // -12171738
  0x555514 to "Pumice", // -11184876
  0x303010 to "Pumpkin", // -13619184
  0xe90367 to "Pure Apple", // -1506457
  0x342bfd to "Purple Heart", // -13358083
  0xc2c287 to "Purple Mountain Majesty", // -4013433
  0x2c2c0d to "Quartz", // -13882355
  0x292909 to "Quicksand", // -14079735
  0x3c3c1c to "Quill Grey", // -12829668
  0xefefcf to "Quincy", // -1052721
  0x007fdf to "Racing Green", // -16744481
  0x484828 to "Radical Red", // -12040152
  0x525232 to "Raffia", // -11382222
  0x222202 to "Rain Forest", // -14540286
  0x555513 to "Rainee", // -11184877
  0x474726 to "Rajah", // -12105946
  0xdedebe to "Rangoon Green", // -2171202
  0x636344 to "Raven", // -10263740
  0x273434 to "Raw Sienna", // -14207948
  0xececcc to "Raw Umber", // -1250100
  0x1110f0 to "Razzle Dazzle Rose", // -15658768
  0x008b6a to "Rebel", // -16741526
  0x464625 to "Red Beech", // -12171739
  0x5f9e7f to "Red Berry", // -10510721
  0x555512 to "Red Damask", // -11184878
  0x30300f to "Red Devil", // -13619185
  0xe90366 to "Red Oxide", // -1506458
  0x342bfc to "Red Stage", // -13358084
  0xc2c286 to "Redwood", // -4013434
  0x2c2c0c to "Regal Blue", // -13882356
  0x292908 to "Regency", // -14079736
  0x3c3c1b to "Regent Grey", // -12829669
  0xefefce to "Remy", // -1052722
  0x007fde to "Reno Sand", // -16744482
  0x484827 to "Resene", // -12040153
  0x525231 to "Resolution Blue", // -11382223
  0x222201 to "Revolver", // -14540287
  0x555511 to "Rhino", // -11184879
  0x474725 to "Rice Cake", // -12105947
  0xdedebd to "Rice Flower", // -2171203
  0x636343 to "Rich Gold", // -10263741
  0x273433 to "Rio Grande", // -14207949
  0xececcb to "Ripe Lemon", // -1250101
  0x1110ef to "River Bed", // -15658769
  0x008b69 to "Rob Roy", // -16741527
  0x5f9e7e to "Rock", // -10510722
  0x464624 to "Rock Blue", // -12171740
  0x555510 to "Rock Spray", // -11184880
  0x30300e to "Rodeo Dust", // -13619186
  0xe90365 to "Rolling Stone", // -1506459
  0x342bfb to "Roman", // -13358085
  0xc2c285 to "Roman Coffee", // -4013435
  0x2c2c0b to "Romance", // -13882357
  0x292907 to "Romantic", // -14079737
  0x3c3c1a to "Ronchi", // -12829670
  0xefefcd to "Roof Terracotta", // -1052723
  0x007fdd to "Rope", // -16744483
  0x484826 to "Rose", // -12040154
  0x525230 to "Rose Bud", // -11382224
  0x222200 to "Rose Bud Cherry", // -14540288
  0x55550f to "Rose Fog", // -11184881
  0x474724 to "Rose White", // -12105948
  0xdedebc to "Rosy Brown", // -2171204
  0x636342 to "Roti", // -10263742
  0x273432 to "Royal Blue", // -14207950
  0xececca to "Royal Heath", // -1250102
  0x1110ee to "Rum", // -15658770
  0x008b68 to "Rum Swizzle", // -16741528
  0x5f9e7d to "Russet", // -10510723
  0x464623 to "Rustic Red", // -12171741
  0x55550e to "Rusty Nail", // -11184882
  0x5f9e7c to "Saddle", // -10510724
  0x464622 to "Saddle Brown", // -12171742
  0x55550d to "Saffron", // -11184883
  0x30300d to "Sage", // -13619187
  0xe90364 to "Sahara", // -1506460
  0x342bfa to "Sahara Sand", // -13358086
  0xc2c284 to "Sail", // -4013436
  0x2c2c0a to "Salem", // -13882358
  0x292906 to "Salmon", // -14079738
  0x3c3c19 to "Salomie", // -12829671
  0xefefcc to "Saltpan", // -1052724
  0x007fdc to "Sand Dune", // -16744484
  0x484825 to "Sandal", // -12040155
  0x52522f to "Sandrift", // -11382225
  0x2221ff to "Sandstone", // -14540289
  0x55550c to "Sanguine Brown", // -11184884
  0x474723 to "Santa Fe", // -12105949
  0xdedebb to "Santas Grey", // -2171205
  0x636341 to "Sapling", // -10263743
  0x273431 to "Sapphire", // -14207951
  0xececc9 to "Saratoga", // -1250103
  0x1110ed to "Sauvignon", // -15658771
  0x008b67 to "Sazerac", // -16741529
  0x5f9e7b to "Scampi", // -10510725
  0x464621 to "Scandal", // -12171743
  0x55550b to "Scarlet", // -11184885
  0x30300c to "Scarpa Flow", // -13619188
  0xe90363 to "Schist", // -1506461
  0x342bf9 to "School Bus Yellow", // -13358087
  0xc2c283 to "Schooner", // -4013437
  0x2c2c09 to "Scooter", // -13882359
  0x292905 to "Scorpion", // -14079739
  0x3c3c18 to "Sea Buckthorn", // -12829672
  0xefefcb to "Sea Green", // -1052725
  0x007fdb to "Sea Mist", // -16744485
  0x484824 to "Sea Nymph", // -12040156
  0x52522e to "Sea Pink", // -11382226
  0x2221fe to "Seagull", // -14540290
  0x55550a to "Seance", // -11184886
  0x474722 to "Seashell", // -12105950
  0xdedeba to "Seaweed", // -2171206
  0x636340 to "Selago", // -10263744
  0x273430 to "Selective Yellow", // -14207952
  0xececc8 to "Sepia", // -1250104
  0x1110ec to "Serenade", // -15658772
  0x008b66 to "Shadow", // -16741530
  0x5f9e7a to "Shadow Green", // -10510726
  0x464620 to "Shalimar", // -12171744
  0x555509 to "Shampoo", // -11184887
  0x30300b to "Shannon", // -13619189
  0xe90362 to "Shark", // -1506462
  0x342bf8 to "Sherpa Blue", // -13358088
  0xc2c282 to "Sherwood Green", // -4013438
  0x2c2c08 to "Shilo", // -13882360
  0x292904 to "Shingle Fawn", // -14079740
  0x3c3c17 to "Ship Cove", // -12829673
  0xefefca to "Ship Grey", // -1052726
  0x007fda to "Shiraz", // -16744486
  0x484823 to "Shocking", // -12040157
  0x52522d to "Shuttle Grey", // -11382227
  0x2221fd to "Siam", // -14540291
  0x555508 to "Sidecar", // -11184888
  0x474721 to "Silk", // -12105951
  0xdedeb9 to "Silver", // -2171207
  0x63633f to "Silver Chalice", // -10263745
  0x27342f to "Silver Sand", // -14207953
  0xececc7 to "Silver Tree", // -1250105
  0x1110eb to "Sinbad", // -15658773
  0x008b65 to "Siren", // -16741531
  0x5f9e79 to "Sirocco", // -10510727
  0x46461f to "Skeptic", // -12171745
  0x555507 to "Slate Grey", // -11184889
  0x30300a to "Slicker", // -13619190
  0xe90361 to "Slime Green", // -1506463
  0x342bf7 to "Smoke", // -13358089
  0xc2c281 to "Smokey Ash", // -4013439
  0x2c2c07 to "Smoky Grey", // -13882361
  0x292903 to "Snow Drift", // -14079741
  0x3c3c16 to "Snow Flurry", // -12829674
  0xefefc9 to "Snowy Mint", // -1052727
  0x007fd9 to "Snuff", // -16744487
  0x484822 to "Soapstone", // -12040158
  0x52522c to "Soft Amber", // -11382228
  0x2221fc to "Soft Apple", // -14540292
  0x555506 to "Soft Peach", // -11184890
  0x474720 to "Solidago", // -12105952
  0xdedeb8 to "Solitaire", // -2171208
  0x27342e to "Solitude", // -14207954
  0x63633e to "Solo", // -10263746
  0xececc6 to "Sorbus", // -1250106
  0x1110ea to "Sorrell Brown", // -15658774
  0x008b64 to "Soya Bean", // -16741532
  0x5f9e78 to "Spanish White", // -10510728
  0x46461e to "Spectra", // -12171746
  0x555505 to "Spice", // -11184891
  0x303009 to "Spicy Mix", // -13619191
  0xe90360 to "Spicy Mustard", // -1506464
  0x342bf6 to "Spindle", // -13358090
  0xc2c280 to "Spray", // -4013440
  0x2c2c06 to "Spring Leaves", // -13882362
  0x292902 to "Spring Rain", // -14079742
  0x3c3c15 to "Spring Sun", // -12829675
  0xefefc8 to "Spring Wood", // -1052728
  0x007fd8 to "Sprout", // -16744488
  0x484821 to "Spun Pearl", // -12040159
  0x52522b to "Squirrel", // -11382229
  0x2221fb to "St Tropaz", // -14540293
  0x555504 to "Stack", // -11184892
  0x47471f to "Star Dust", // -12105953
  0xdedeb7 to "Stark White", // -2171209
  0x63633d to "Starship", // -10263747
  0x27342d to "Steel Grey", // -14207955
  0xececc5 to "Stiletto", // -1250107
  0x1110e9 to "Stone Age", // -15658775
  0x008b63 to "Stone Hand", // -16741533
  0x5f9e77 to "Stonegrey", // -10510729
  0x46461d to "Storm Dust", // -12171747
  0x555503 to "Storm Grey", // -11184893
  0x303008 to "Stratos", // -13619192
  0xe9035f to "Straw", // -1506465
  0x342bf5 to "Strikemaster", // -13358091
  0xc2c27f to "Stromboli", // -4013441
  0x2c2c05 to "Studio", // -13882363
  0x292901 to "Submarine", // -14079743
  0x3c3c14 to "Sugar Cane", // -12829676
  0xefefc7 to "Sulu", // -1052729
  0x007fd7 to "Summer Green", // -16744489
  0x484820 to "Sun", // -12040160
  0x52522a to "Sundance", // -11382230
  0x2221fa to "Sundown", // -14540294
  0x555502 to "Sunflower", // -11184894
  0x47471e to "Sunglo", // -12105954
  0xdedeb6 to "Sunglow", // -2171210
  0x63633c to "Sunray", // -10263748
  0x27342c to "Sunset", // -14207956
  0xececc4 to "Sunset Orange", // -1250108
  0x1110e8 to "Sunzane", // -15658776
  0x008b62 to "Supa", // -16741534
  0x5f9e76 to "Surf", // -10510730
  0x46461c to "Surf Crest", // -12171748
  0x555501 to "Surfie Green", // -11184895
  0x303007 to "Sushi", // -13619193
  0xe9035e to "Suva Grey", // -1506466
  0x342bf4 to "Swamp", // -13358092
  0xc2c27e to "Swansdown", // -4013442
  0x2c2c04 to "Sweet Corn", // -13882364
  0x292900 to "Sweet Pink", // -14079744
  0x3c3c13 to "Swirl", // -12829677
  0xefefc6 to "Swiss Coffee", // -1052730
  0x007fd6 to "Sycamore", // -16744490
  0x48481f to "Tabasco", // -12040161
  0x525229 to "Tacao", // -11382231
  0x2221f9 to "Tacha", // -14540295
  0x555500 to "Tahiti Gold", // -11184896
  0x47471d to "Tahuna Sands", // -12105955
  0xdedeb5 to "Tall Poppy", // -2171211
  0x63633b to "Tallow", // -10263749
  0x27342b to "Tamarillo", // -14207957
  0xececc3 to "Tamarind", // -1250109
  0x1110e7 to "Tan", // -15658777
  0x008b61 to "Tana", // -16741535
  0x5f9e75 to "Tangaroa", // -10510731
  0x46461b to "Tangerine", // -12171749
  0x5554ff to "Tango", // -11184897
  0x303006 to "Tapa", // -13619194
  0xe9035d to "Tapestry", // -1506467
  0x342bf3 to "Tara", // -13358093
  0xc2c27d to "Tarawera", // -4013443
  0x2c2c03 to "Tarnished Silver", // -13882365
  0x2928ff to "Tasman", // -14079745
  0x3c3c12 to "Taupe", // -12829678
  0xefefc5 to "Taupe Grey", // -1052731
  0x007fd5 to "Tawny Port", // -16744491
  0x48481e to "Te Papa", // -12040162
  0x525228 to "Tea", // -11382232
  0x2221f8 to "Teak", // -14540296
  0x5554fe to "Teal", // -11184898
  0x47471c to "Teal Blue", // -12105956
  0xdedeb4 to "Temptress", // -2171212
  0x63633a to "Tequila", // -10263750
  0x27342a to "Terra", // -14207958
  0xececc2 to "Terracotta", // -1250110
  0x1110e6 to "Texas", // -15658778
  0x008b60 to "Texas Rose", // -16741536
  0x5f9e74 to "Thatch", // -10510732
  0x46461a to "Thistle", // -12171750
  0x5554fd to "Thulian Pink", // -11184899
  0x303005 to "Thunder", // -13619195
  0xe9035c to "Thunderbird", // -1506468
  0x342bf2 to "Tia Maria", // -13358094
  0xc2c27c to "Tiara", // -4013444
  0x2c2c02 to "Tiber", // -13882366
  0x2928fe to "Tickled Pink", // -14079746
  0x3c3c11 to "Tidal", // -12829679
  0xefefc4 to "Tide", // -1052732
  0x007fd4 to "Timber Green", // -16744492
  0x48481d to "Timberwolf", // -12040163
  0x525227 to "Titanium", // -11382233
  0x2221f7 to "Toast", // -14540297
  0x5554fc to "Tobacco Brown", // -11184900
  0x47471b to "Toledo", // -12105957
  0xdedeb3 to "Tolopea", // -2171213
  0x636339 to "Tom Thumb", // -10263751
  0x273429 to "Tonys Pink", // -14207959
  0xececc1 to "Topaz", // -1250111
  0x1110e5 to "Tora", // -15658779
  0x008b5f to "Tornado", // -16741537
  0x5f9e73 to "Tosca", // -10510733
  0x464619 to "Totem Pole", // -12171751
  0x5554fb to "Tower Grey", // -11184901
  0x303004 to "Tradewind", // -13619196
  0xe9035b to "Tranquil", // -1506469
  0x342bf1 to "Travertine", // -13358095
  0xc2c27b to "Tree Poppy", // -4013445
  0x2c2c01 to "Treehouse", // -13882367
  0x2928fd to "Trend", // -14079747
  0x3c3c10 to "Trinidad", // -12829680
  0xefefc3 to "Tropical Blue", // -1052733
  0x007fd3 to "True Blue", // -16744493
  0x48481c to "Tuatara", // -12040164
  0x525226 to "Tuft Bush", // -11382234
  0x2221f6 to "Tulip Tree", // -14540298
  0x5554fa to "Tumbleweed", // -11184902
  0x47471a to "Tuna", // -12105958
  0xdedeb2 to "Tundora", // -2171214
  0x636338 to "Turbo", // -10263752
  0x273428 to "Turkish Rose", // -14207960
  0xececc0 to "Turmeric", // -1250112
  0x1110e4 to "Turquoise", // -15658780
  0x008b5e to "Turquoise Blue", // -16741538
  0x5f9e72 to "Tuscany", // -10510734
  0x464618 to "Tusk", // -12171752
  0x5554f9 to "Tussock", // -11184903
  0x303003 to "Tutu", // -13619197
  0xe9035a to "Twilight", // -1506470
  0x342bf0 to "Twilight Blue", // -13358096
  0xc2c27a to "Twine", // -4013446
  0x2c2c00 to "Tyrian Purple", // -13882368
  0x2928fc to "Ultramarine", // -14079748
  0x3c3c0f to "Valencia", // -12829681
  0xefefc2 to "Valentino", // -1052734
  0x007fd2 to "Valhalla", // -16744494
  0x48481b to "Van Cleef", // -12040165
  0x525225 to "Vanilla", // -11382235
  0x2221f5 to "Varnish", // -14540299
  0x5554f8 to "Vesuvius", // -11184904
  0x474719 to "Victoria", // -12105959
  0xdedeb1 to "Viola", // -2171215
  0x636337 to "Violent Violet", // -10263753
  0x273427 to "Violet", // -14207961
  0xececbf to "Violet Eggplant", // -1250113
  0x1110e3 to "Viridian Green", // -15658781
  0x008b5d to "Vis Vis", // -16741539
  0x5f9e71 to "Vista Blue", // -10510735
  0x464617 to "Vista White", // -12171753
  0x5554f7 to "Vivid Tangerine", // -11184905
  0x303002 to "Voodoo", // -13619198
  0xe90359 to "Vulcan", // -1506471
  0x342bef to "Waikawa Grey", // -13358097
  0xc2c279 to "Waiouru", // -4013447
  0x2c2bff to "Walnut", // -13882369
  0x2928fb to "Wasabi", // -14079749
  0x3c3c0e to "Water Leaf", // -12829682
  0xefefc1 to "Waterloo", // -1052735
  0x007fd1 to "Wattle", // -16744495
  0x48481a to "Watusi", // -12040166
  0x525224 to "Wax Flower", // -11382236
  0x2221f4 to "We Peep", // -14540300
  0x5554f6 to "Wedgewood", // -11184906
  0x474718 to "Wheat", // -12105960
  0xdedeb0 to "Wheatfield", // -2171216
  0x636336 to "Whiskey", // -10263754
  0x273426 to "Whisper", // -14207962
  0xececbe to "White", // -1250114
  0x1110e2 to "White Pointer", // -15658782
  0x008b5c to "White Rock", // -16741540
  0x5f9e70 to "Wild Blue Yonder", // -10510736
  0x464616 to "Wild Rice", // -12171754
  0x5554f5 to "Wild Sand", // -11184907
  0x303001 to "Wild Strawberry", // -13619199
  0xe90358 to "Wild Willow", // -1506472
  0x342bee to "William", // -13358098
  0xc2c278 to "Willow Brook", // -4013448
  0x2c2bfe to "Willow Grove", // -13882370
  0x2928fa to "Windsor Tan", // -14079750
  0x3c3c0d to "Wine Berry", // -12829683
  0xefefc0 to "Winter Hazel", // -1052736
  0x007fd0 to "Wisp Pink", // -16744496
  0x484819 to "Wistful", // -12040167
  0x525223 to "Witch Hazel", // -11382237
  0x2221f3 to "Wood Bark", // -14540301
  0x5554f4 to "Woodland", // -11184908
  0x474717 to "Woodsmoke", // -12105961
  0xdedeaf to "Woody Brown", // -2171217
  0x636335 to "Xanadu", // -10263755
  0x273425 to "Yellow", // -14207963
  0xececbd to "Yellow Metal", // -1250115
  0x1110e1 to "Yellow Sea", // -15658783
  0x008b5b to "Your Pink", // -16741541
  0x5f9e6f to "Zambezi", // -10510737
  0x464615 to "Zanah", // -12171755
  0x5554f3 to "Zest", // -11184909
  0x303000 to "Zeus", // -13619200
  0xe90357 to "Ziggurat", // -1506473
  0x342bed to "Zinnwaldite", // -13358099
  0xc2c277 to "Zircon", // -4013449
  0x2c2bfd to "Zombie", // -13882371
  0x2928f9 to "Zorba", // -14079751
  0x3c3c0c to "Zucca", // -12829684
  0xefefbf to "Zuma", // -1052737
)
