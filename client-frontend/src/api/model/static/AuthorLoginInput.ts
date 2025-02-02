export interface AuthorLoginInput {
    readonly authorId?: number | undefined;
    readonly authorName?: string | undefined;
    readonly captcha?: string | undefined;
    readonly email?: string | undefined;
    readonly password: string;
}
