--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

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

SET default_table_access_method = heap;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    age integer NOT NULL,
    id bigint NOT NULL,
    address character varying(255),
    email character varying(255),
    last_name character varying(255),
    name character varying(255),
    password character varying(255),
    phone character varying(255),
    rut character varying(255),
    appraisal_certificate bytea,
    buisness_plan bytea,
    credit_history bytea,
    deed_frist_property bytea,
    financial_statements bytea,
    income_statement bytea,
    remodeling_budget bytea,
    updated_aprraisal_certificate bytea
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.client ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: credit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit (
    amount double precision NOT NULL,
    interest_rate double precision NOT NULL,
    monthly_payment double precision NOT NULL,
    years integer NOT NULL,
    id bigint NOT NULL,
    type character varying(255)
);


ALTER TABLE public.credit OWNER TO postgres;

--
-- Name: credit_evaluation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit_evaluation (
    amount double precision NOT NULL,
    interest_rate double precision NOT NULL,
    years integer NOT NULL,
    id bigint NOT NULL,
    rut character varying(255),
    status character varying(255)
);


ALTER TABLE public.credit_evaluation OWNER TO postgres;

--
-- Name: credit_evaluation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.credit_evaluation ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.credit_evaluation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: credit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.credit ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.credit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: loan_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loan_type (
    finance_rate double precision NOT NULL,
    interest_rate double precision NOT NULL,
    years integer NOT NULL,
    id bigint NOT NULL,
    type character varying(255),
    rut_client character varying(255),
    monthly_payment double precision DEFAULT 0,
    total_cost double precision DEFAULT 0
);


ALTER TABLE public.loan_type OWNER TO postgres;

--
-- Name: loan_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.loan_type ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.loan_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (age, id, address, email, last_name, name, password, phone, rut, appraisal_certificate, buisness_plan, credit_history, deed_frist_property, financial_statements, income_statement, remodeling_budget, updated_aprraisal_certificate) FROM stdin;
1313131	14	3	231	23133	3213	\N	31313	asdad	\N	\N	\N	\N	\N	\N	\N	\N
2132132131	15	1	321321	1232131	1321313	\N	321321132	123211321	\N	\N	\N	\N	\N	\N	\N	\N
10	16	2	juan.perez@example.com	Pérez	Juan	password123	123456789	12345678-9	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: credit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit (amount, interest_rate, monthly_payment, years, id, type) FROM stdin;
\.


--
-- Data for Name: credit_evaluation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit_evaluation (amount, interest_rate, years, id, rut, status) FROM stdin;
\.


--
-- Data for Name: loan_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.loan_type (finance_rate, interest_rate, years, id, type, rut_client, monthly_payment, total_cost) FROM stdin;
90	8	23	24	primera vivienda	321313	29125	8049675
\.


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 16, true);


--
-- Name: credit_evaluation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_evaluation_id_seq', 1, false);


--
-- Name: credit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_id_seq', 1, false);


--
-- Name: loan_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_type_id_seq', 24, true);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: credit_evaluation credit_evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_evaluation
    ADD CONSTRAINT credit_evaluation_pkey PRIMARY KEY (id);


--
-- Name: credit credit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit
    ADD CONSTRAINT credit_pkey PRIMARY KEY (id);


--
-- Name: loan_type loan_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_type
    ADD CONSTRAINT loan_type_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
