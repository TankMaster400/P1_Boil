## ogólne (już zrobione):
1. Node (class) - klasa mająca węzły:
   1. name - nazwa
   2. next - wszystkie czynności wychodzące z węzła
   3. prev - wszystkie czynności wchodzące do węzła
   4. time_pk - czas liczony od początku do końca
   4. time_kp - czas liczony od końca do początku
   5. time_l - czas l
   5. pom_int - pomocniczy int przy przeliczaniu pk i kp.
2. Czyn (class) - klasa mająca czynności:
   1. name - nazwa
   2. time - czas trwania
   3. prev - obiekt węzła (node) od ktorego zaczyna się czynność.
   4. next - obiekt węzła (node) na którym kończy się czynność.
3. Data (class) - klasa ogólna zawierająca dane:
   1. nodes - tablica węzłów (wszystkich)
   2. czyns - tablica czynności (wszystkich)
   3. addRecord() - funkcja dodająca rekord do danych
   4. (w trakcie) licz() - funkcja licząca wszystkie czasy (bez ścieżki krytycznej)
   5. time_pk() - funkcja licząca rekurencyjnie czas pk.
   5. time_kp() - funkcja licząca rekurencyjnie czas kp.

## do zrobienia jeszcze (na później):
1. Obsługa wielu punktów startowych (na razie komunikat w termianlu)
1. Obsługa wielu punktów końcowych (na razie komunikat w termianlu)
2. Zdarzenie w którym dwie czynności mają ten sam węzeł początkowy i końcowy (? trzeba przemyśleć).

## Dla Franka do ogarnięcia:
1. Graf żeby nie tworzyły się zduplikowane punkty 
2. Zniwelowanie błędów wyskakujących w konsoli (Nie może być więcej niż jeden punkt startowy!|Nie może być więcej niż jeden punkt końcowy!)
3. Nazwy nad strzałkami
4. Dane punktów - musi się dla każdego punktu w jakiś sposób wyświetlać jeszcze:
   1. time_pk - czas liczony od początku do końca
   2. time_kp - czas liczony od końca do początku
   3. time_l - czas l