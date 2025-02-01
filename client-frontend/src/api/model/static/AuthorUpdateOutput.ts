import type {TimeSource} from './index.ts';

export interface AuthorUpdateOutput {
    readonly authorId: number;
    readonly authorName: string;
    readonly bio?: string | undefined;
    readonly contactInfo?: string | undefined;
    readonly email: string;
    readonly githubLink?: string | undefined;
    readonly joinedTime?: TimeSource | undefined;
    readonly password: string;
    readonly verified: boolean;
    readonly weiboLink?: string | undefined;
}
