ALTER TABLE public.courses DISABLE TRIGGER ALL;

INSERT INTO public.courses
    (name, category, active)
VALUES
    ('Java Course',          'Programming',      TRUE),
    ('Python Course',        'Programming',      TRUE),
    ('GO Course',            'Programming',      TRUE),
    ('JavaScript Course',    'Programming',      TRUE),
    ('PHP Course',           'Programming',      TRUE),
    ('Erlang Course',        'Programming',      FALSE),
    ('C# Course ',           'Programming',      FALSE),
    ('Portuguese Course',    'Secular',          TRUE),
    ('English Course',       'Secular',          TRUE),
    ('Italian Course',       'Secular',          TRUE),
    ('Japanese Course',      'Secular',          FALSE),
    ('Chinese Course',       'Secular',          FALSE);

ALTER TABLE public.courses ENABLE TRIGGER ALL;