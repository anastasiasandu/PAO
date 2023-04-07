# Proiect PAO

## Gestiunea Spitalelor

Acest proiect retine informatii despre spitale, impreuna cu pacientii si doctorii acestor spitale, cat si despre programarile, consulturile si internarile pacientilor.

### Ierarhia claselor

#### Model

- Spital
- Persoana
- Pacient
- Doctor
- Programare
- Consult
- Internare
- Reteta

#### Controller

- PacientController
- DoctorController
- ConsultController
- RetetaController

#### Repository

- PacientRepository
- DoctorRepository
- ConsultRepository
- RetetaRepository

#### Service

- PacientService
- DoctorService
- ConsultService
- RetetaService

#### Main

In Main se afla meniul CRUD care prezinta urmatoarele functionalitati:

Functionalitatile de baza pe toate cele 4 clase:
1. Create and Add
2. Update by Id
3. Delete by Id
4. Get by Id
5. List all

Functionalitati personalizate:

Pe clasa Pacient:
6. Verific daca exista un doctor disponibil pentru un pacient cu o problema anume si la o data anume

Pe clasa Doctor:
7. Sortez toti doctorii descrescator dupa salariul lor
8. Gasesc doctorul care lucreaza cel mai mult si ii maresc salariul cu 15%

Pe clasa Reteta:
9. Verific daca reteta unui pacient introdus de la tastatura contine medicamente la care acesta are alergii si in caz afirmativ, le elimin

Pe clasa Consult:
10. Caut pretul consultului unui pacient introdus de la tastatura

#### Mostenirile

Clasele Doctor si Pacient mostenesc clasa Persoana

#### Structurile de date pe care le folosesc

- In Pacient: ArrayList<String>
- In Doctor: SortedMap<String, List<Integer>>
- In Reteta: ArrayList<String>
- In PacientRepository, DoctorRepository, ConsultRepository, RetetaRepository: ArrayList<> 
