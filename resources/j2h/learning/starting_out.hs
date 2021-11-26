lostNumbers = [4,8,15,16,23,42]

listExample1 = [1,2,3,4] ++ [9,10,11,12]  
listExample2 = "hello" ++ " " ++ "world"
listExample3 = ['w','o'] ++ ['o','t'] 
listExample4 = take 2 ([1] ++ [2]) 
listExample5 = take 100 ([1] ++ [3] ++ [4] ++ [5])


consExample1 = 'A':" SMALL CAT"
consExample2 = 5:[1,2,3,4,5] 

getExample1 = "Steve Buscemi" !! 6  
getExample2 = [9.4,33.2,96.2,11.2,23.25] !! 1 

list1 = [[1,2,3,4],[5,3,3,3],[1,2,2,3,4],[1,2,3]]  
listsExample1 = list1 ++ [[1,1,1,1]]  
listsExample2 = [6,6,6]:list1  
listsExample3 = list1 !! 2  

listComp1 = [3,2,1] > [2,1,0]  
listComp2 = [3,2,1] > [2,10,100]  
listComp3 = [3,4,2] > [3,4]  
listComp4 = [3,4,2] > [2,4]  
listComp5 = [3,4,2] == [3,4,2]
listComp6 = [3,4,2] < [4,4]

listOp1 = head [5,4,3,2,1] 
listOp2 = tail [5,4,3,2,1]
listOp3 = last [5,4,3,2,1]
listOp4 = init [5,4,3,2,1]

lengthTest = length [5, 4, 3, 2, 1]
nullTest1 = null [1, 2, 3]
nullTest2 = null []
reverseTest = reverse [5, 4, 3, 2, 1]
takeTest1 = take 3 [5, 4, 3, 2, 1]
takeTest2 = take 5 [1, 2]
takeTest3 = take 0 [6, 6, 6]

dropTest1 = drop 3 [8, 4, 2, 1, 5, 6]
dropTest2 = drop 0 [1, 2, 3, 4]
dropTest3 = drop 100 [1, 2, 3, 4]

minimumTest = minimum [8, 4, 2, 1, 5, 6]
maximumTest = maximum [1, 9, 2, 3, 4]

sumTest1 = sum []
sumTest2 = sum [5, 2, 1, 6, 3, 2, 5, 7]

productTest1 = product [6, 2, 1, 2]
productTest2 = product [1, 2, 5, 6, 7, 9, 2, 0]

elemTest1 = elem 4 [3, 4, 5, 6]
elemTest2 = elem 10 [3, 4, 5, 6]

range1 = [1..20]   
range2 = ['a'..'z']
range3 = ['Z'..'a']
range4 = ['z'..'A']
range5 = ['K'..'Z'] 
range6 = [2,4..20]
range7 = [3,6..20]
range8 = [2,5..15]
range9 = [20,19..1]
range10 = take 24 [26..]
range11 = take 24 [28, 26..]

infList1 = take 3 ([4] ++ [2..])
infList2 = [2..] !! 1000

cycle1 = cycle [1, 2] !! 0
cycle2 = cycle [1, 2] !! 1
cycle3 = cycle [1, 2] !! 1000
cycle4 = take 10 (cycle [1,2,3])  
cycle5 = take 12 (cycle "LOL ")  

repeat1 = take 10 (repeat 5)  

replicate1 = replicate 3 10

comp1 = [x * 2 | x <- [1..10]]
comp2 = [x*2 | x <- [1..10], x*2 >= 12]
comp3 = [ x | x <- [50..100], x `mod` 7 == 3]
comp4 = [x | x <- [50..100], x `mod` 7 == 3, x > 70]
boomBangs xs = [ if x < 10 then "BOOM!" else "BANG!" | x <- xs, odd x] 
comp5 = boomBangs [7..13]
comp6 = [ x | x <- [10..20], x /= 13, x /= 15, x /= 19]  
comp7 = [ x*y | x <- [2,5,10], y <- [8,10,11]]
comp8 = [ x*y | x <- [2,5,10], y <- [8,10,11], x*y > 50]

nouns = ["hobo","frog","pope"]
adjectives = ["lazy","grouchy","scheming"]
comp9 = [adjective ++ " " ++ noun | adjective <- adjectives, noun <- nouns]

length' xs = sum [1 | _ <- xs]

removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z']]
comp10 = removeNonUppercase "Hahaha! Ahahaha!"
comp11 = removeNonUppercase "IdontLIKEFROGS"

xxs = [[1,3,5,2,3,1,2,4,5],[1,2,3,4,5,6,7,8,9],[1,2,4,2,1,6,3,1,3,2,3,6]]  
comp12 = [ [ x | x <- xs, even x ] | xs <- xxs]  

tuple1 = fst (8,11)
tuple2 = fst ("Wow", False)
tuple3 = snd (8,11)
tuple4 = snd ("Wow", False)
tuple5 = zip [1,2,3,4,5] [5,5,5,5,5]
tuple6 = zip [1 .. 5] ["one", "two", "three", "four", "five"]
tuple7 = zip [5,3,2,6,2,7,2,5,4,6,6] ["im","a","turtle"]
tuple8 = zip [1..] ["apple", "orange", "cherry", "mango"]
tuple9 = [ (a,b,c) | c <- [1..10], b <- [1..c], a <- [1..b], a^2 + b^2 == c^2]  
tuple10 = [ (a,b,c) | c <- [1..10], b <- [1..c], a <- [1..b], a^2 + b^2 == c^2, a+b+c == 24]  