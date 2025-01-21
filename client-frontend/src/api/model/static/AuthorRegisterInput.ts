export interface AuthorRegisterInput {
    readonly authorId?: number | undefined;
    readonly authorName: string;
    readonly captcha: string;
    readonly email: string;
    readonly password: string;
}
