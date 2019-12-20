create table mails
(
    id            bigint primary key,
    template_id   bigint references templates (id),
    creation_time date
)