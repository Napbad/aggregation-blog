export interface AuthorLoginInput {
    readonly authorId?: number | undefined;
    readonly authorName?: string;
    readonly captcha?: string | undefined;
    readonly email: string;
    readonly password: string;
}
