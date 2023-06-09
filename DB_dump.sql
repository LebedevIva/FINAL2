--
-- PostgreSQL database dump
--

-- Dumped from database version 10.23
-- Dumped by pg_dump version 10.23

-- Started on 2023-04-21 23:17:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 24577)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 24580)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 198 (class 1259 OID 24582)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24585)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.image ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 200 (class 1259 OID 24587)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp without time zone,
    number character varying(255),
    price numeric(10,2) NOT NULL,
    status integer,
    person_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24590)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 202 (class 1259 OID 24592)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    login character varying(20),
    password character varying(255),
    role character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24598)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 24600)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    date_time_of_create timestamp without time zone,
    description text NOT NULL,
    price numeric(10,2) NOT NULL,
    seller character varying(255) NOT NULL,
    title text NOT NULL,
    warehouse character varying(255) NOT NULL,
    category_id integer NOT NULL,
    date_time_of_created timestamp without time zone,
    CONSTRAINT product_price_check CHECK (((price)::double precision >= (1)::double precision))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24607)
-- Name: product_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);


ALTER TABLE public.product_cart OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24610)
-- Name: product_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product_cart ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 24612)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2844 (class 0 OID 24577)
-- Dependencies: 196
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (3, 'Аксессуары');
INSERT INTO public.category (id, name) VALUES (1, 'Обувь');
INSERT INTO public.category (id, name) VALUES (2, 'Одежда');


--
-- TOC entry 2846 (class 0 OID 24582)
-- Dependencies: 198
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.image (id, file_name, product_id) VALUES (5, '3b71b6fe-f9cb-4b8d-b4e4-e9f7b260637a.5.avif', 16);
INSERT INTO public.image (id, file_name, product_id) VALUES (6, 'ab8a1da8-a1f3-42b0-b397-05d244c61043.1.avif', 16);
INSERT INTO public.image (id, file_name, product_id) VALUES (7, '76b6f2ca-2a2c-4957-995b-a66ebaca2d04.2.avif', 16);
INSERT INTO public.image (id, file_name, product_id) VALUES (8, 'c7c24ca7-839d-489e-a588-022c864b5b2f.3.avif', 16);
INSERT INTO public.image (id, file_name, product_id) VALUES (9, '36b11b03-cb1d-4703-9e75-2e85f5d645b0.4.avif', 16);
INSERT INTO public.image (id, file_name, product_id) VALUES (10, 'ecd37ff7-45e3-465b-a565-a1c8d889e938.11.avif', 17);
INSERT INTO public.image (id, file_name, product_id) VALUES (11, '333d157f-4baa-484a-b590-732d00920c9d.22.avif', 17);
INSERT INTO public.image (id, file_name, product_id) VALUES (12, '2ce7c550-759e-4465-838f-81ce6309d4c6.33.avif', 17);
INSERT INTO public.image (id, file_name, product_id) VALUES (13, '8bf2a5cb-ed64-42af-b960-ae258539aff0.1_1.avif', 18);
INSERT INTO public.image (id, file_name, product_id) VALUES (14, 'b9ae643e-3e64-4263-beec-5087962ec9fb.1_2.avif', 18);
INSERT INTO public.image (id, file_name, product_id) VALUES (15, '7d849dde-36a1-41a1-b144-7f95b983e2ae.1_3.avif', 18);
INSERT INTO public.image (id, file_name, product_id) VALUES (16, '89a0a8c7-e709-4f07-bc9a-544d5b88c5a6.2_1.avif', 19);
INSERT INTO public.image (id, file_name, product_id) VALUES (17, '5193c6ec-61fb-47e9-8236-afecc03ee289.2_2.avif', 19);
INSERT INTO public.image (id, file_name, product_id) VALUES (18, '30297682-4098-4ee5-baf5-44a5ec18fa17.2_3.avif', 19);
INSERT INTO public.image (id, file_name, product_id) VALUES (19, 'bd42499a-d62b-4507-9c23-6f669fd4435f.3_1.avif', 20);
INSERT INTO public.image (id, file_name, product_id) VALUES (20, '90a79201-1e1d-4f7e-997c-f6cdc3ce0ec2.3_2.avif', 20);
INSERT INTO public.image (id, file_name, product_id) VALUES (21, '142a3ef5-91ba-4e4e-9f56-0b166c373352.3_3.avif', 20);
INSERT INTO public.image (id, file_name, product_id) VALUES (22, '1c1156df-1bfe-4fc0-b2cc-59e85f5049f2.4_1.avif', 21);
INSERT INTO public.image (id, file_name, product_id) VALUES (23, '860a8bf6-77ac-4f72-95eb-f7a4c1f155e5.4_2.avif', 21);
INSERT INTO public.image (id, file_name, product_id) VALUES (24, '24286462-a9e2-484f-96a4-36c3465c2b28.4_3.avif', 21);


--
-- TOC entry 2848 (class 0 OID 24587)
-- Dependencies: 200
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, count, date_time, number, price, status, person_id, product_id) VALUES (6, 1, '2023-04-21 23:11:46.128823', 'e1480e00-262b-4640-9ff1-17b025aae129', 11000.00, 0, 7, 17);
INSERT INTO public.orders (id, count, date_time, number, price, status, person_id, product_id) VALUES (7, 1, '2023-04-21 23:11:46.138825', 'e1480e00-262b-4640-9ff1-17b025aae129', 8000.00, 0, 7, 18);


--
-- TOC entry 2850 (class 0 OID 24592)
-- Dependencies: 202
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.person (id, login, password, role) VALUES (1, 'admin', '$2a$10$Tm2wOz7RVG6ZXS7w8hF.wugg3OettsdVU7Uinl5yyhYCIm/x3DdY.', 'ROLE_ADMIN');
INSERT INTO public.person (id, login, password, role) VALUES (3, 'admin1', '$2a$10$1w9SiLTocrYPBrPSJM.h2OFIkbn9r74BkAwAGJ6RghcHaMLmUrTEC', 'ROLE_ADMIN');
INSERT INTO public.person (id, login, password, role) VALUES (4, 'test123', '$2a$10$YsqxaOY9KAWNZHnOIPxTheE6fDbttgdHIRjZt3G/z2ILLajDZpUze', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (6, 'test4', '$2a$10$/w0TrXk2Ynyg6J.FnoWor.tsw0aZtfMBvANRlG8QI0Urc3T1pIMze', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (7, 'login4', '$2a$10$VLbjLNFhfIxdaD65ZvlcTOYvOGd8Rr58fiqiM1M4eZ4zW6MDDRxCq', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (8, 'fsdfds', '$2a$10$DlzqlDZ0aU1gD2Ly47w2dOSN9h1shEOXathwY6GFdX6LdP0fpNDzm', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (2, 'user_test0', '$2a$10$XhHyDP0f6J6VDwvbaoDF5.bjITq4PfGRev3GmTNShrzhfmksGn8nO', 'ROLE_ADMIN');
INSERT INTO public.person (id, login, password, role) VALUES (5, 'user_final', '$2a$10$yzvtw7sNUXvDSTMYh9qL3e40VY3Xba1bu9qWQBnBGR3cgrLje2gKq', 'ROLE_ADMIN');


--
-- TOC entry 2852 (class 0 OID 24600)
-- Dependencies: 204
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (17, '2023-04-21 17:57:49.433031', 'Nike''s ''Blazer Low ''77'' sneakers are a remake of the original basketball style from the 1970s. Printed with the logo across the heels, this suede-trimmed pair has retro foam tongues, oversized leather ''Swooshes'' and herringbone-patterned vulcanised rubber soles.', 11000.00, 'NIKE', 'Blazer Low ''77 Suede-Trimmed Leather Sneakers', 'Санкт Петербург', 1, NULL);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (16, '2023-04-21 17:55:44.791623', 'Chelsea boots are a timeless, easy-to style silhouette that''ll get you through so many cold-weather occasions. Belstaff''s ''Longton'' version is made from leather in endlessly versatile black. They have elasticated gusseted sides and stacked leather midsoles, as well as handy pull tabs at the front and back.', 18000.00, 'BELSTAFF', 'Longton Leather Chelsea Boots', 'Москва', 1, NULL);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (18, NULL, 'Loewe''s T-shirt is made in collaboration with Paula''s Ibiza, the famous Balearic boutique founded at the height of hippie culture in the ''70s. It''s cut from cotton-jersey and printed with the brand''s name and delicate flowers.', 8000.00, 'LOEWE', 'Paula''s Ibiza Printed Cotton-Jersey T-Shirt', 'Москва', 2, NULL);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (19, '2023-04-21 18:04:13.312492', 'Officine Générale''s ''Leo'' jacket is a sleek take on a traditional trucker. It’s made from supple leather with a smooth lining and detailed with patch pockets, which are more streamlined than the usual flap styles. The hide will get softer and develop a unique patina with wear.', 40000.00, 'OFFICINE GÉNÉRALE', 'Leo Leather Jacket', 'Санкт Петербург', 2, NULL);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (20, '2023-04-21 18:05:49.199748', 'Each Anderson''s belt is crafted in Parma, Italy, in a lengthy process that involves over 100 steps, each precisely carried out by hand. Designed with a burnished gold roller buckle, this one is woven from brown leather that will develop a nice patina over time.', 6000.00, 'ANDERSON''S', '3.5cm Woven Leather Belt', 'Москва', 3, NULL);
INSERT INTO public.product (id, date_time_of_create, description, price, seller, title, warehouse, category_id, date_time_of_created) VALUES (21, '2023-04-21 18:07:09.808121', 'Loewe''s sunglasses have prominent, oval-shaped frames that are both timeless and statement-making. Part of the house''s collaboration with Paula''s Ibiza, they''re cast from acetate and detailed with a gold-tone cursive logo on the side.', 12000.00, 'LOEWE', 'Paula''s Ibiza Oval-Frame Acetate Sunglasses', 'Москва', 3, NULL);


--
-- TOC entry 2853 (class 0 OID 24607)
-- Dependencies: 205
-- Data for Name: product_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 197
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 3, true);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 199
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 24, true);


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 201
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 7, true);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 203
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 8, true);


--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 206
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 23, true);


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 207
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 21, true);


--
-- TOC entry 2704 (class 2606 OID 24615)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 24617)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 24619)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 24621)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 24623)
-- Name: product_cart product_cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 24625)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 24627)
-- Name: product uk_qka6vxqdy1dprtqnx9trdd47c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_qka6vxqdy1dprtqnx9trdd47c UNIQUE (title);


--
-- TOC entry 2718 (class 2606 OID 24628)
-- Name: orders fk1b0m4muwx1t377w9if3w6wwqn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 2720 (class 2606 OID 24633)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2719 (class 2606 OID 24638)
-- Name: orders fk787ibr3guwp6xobrpbofnv7le; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk787ibr3guwp6xobrpbofnv7le FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2717 (class 2606 OID 24643)
-- Name: image fkgpextbyee3uk9u6o2381m7ft1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2721 (class 2606 OID 24648)
-- Name: product_cart fkhpnrxdy3jhujameyod08ilvvw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2722 (class 2606 OID 24653)
-- Name: product_cart fksgnkc1ko2i1o9yr2p63ysq3rn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-04-21 23:17:36

--
-- PostgreSQL database dump complete
--

