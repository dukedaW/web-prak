INSERT INTO job_seeker (id, login, password, full_name, contact_info, education_info, status, 
						desired_position, desired_salary) 
VALUES (1, 'zaharova01', 'f8en30f7', 'Захарова Марьяна Никитична', 
		json_build_object('email', 'zaharova@mail.ru' ,'Телефон','89212780971','Адрес', 'ул. Гагарина, д. 10, кв.134'),
	   'МГУ, факультет ВМК', 'Ищет работу', 'Разработчик', 100000);
	   
	   
INSERT INTO job_seeker (id, login, password, full_name, contact_info, education_info, status, 
						desired_position, desired_salary) 
VALUES (2, 'ponomarev02', 'j7m0bf6v', 'Пономарев Даниил Евгеньевич', 
		json_build_object('email', 'ponomarev@mail.ru' ,'Телефон','89115109379','Адрес', 'ул. Ленина, д. 35, кв.3'),
	   'СПбГУ, факультет менеджмента', 'Ищет работу', 'Начальник отдела продаж', 200000);
	   
	   


INSERT INTO job_seeker (id, login, password, full_name, contact_info, education_info, status, 
						desired_position, desired_salary) 
VALUES (3, 'belyaeva03', 'fg74n20j', 'Беляева Екатерина Александровна', 
		json_build_object('email', 'belyaeva@mail.ru' ,'Телефон','89112325875','Адрес', 'ул. Рябиновая, д. 24, кв.100'),
	   'Коледж финансов', 'Ищет работу', 'Главный бухгалтер', 50000);