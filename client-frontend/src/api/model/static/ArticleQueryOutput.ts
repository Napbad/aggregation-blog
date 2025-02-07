import type {TimeSource} from './';

export interface ArticleQueryOutput {
    readonly articleId: number;
    readonly authorId: number;
    readonly category?: string | undefined;
    readonly content: string;
    readonly copyrightInfo?: string | undefined;
    readonly publishTime: TimeSource;
    readonly title: string;
    readonly version: number;
}
