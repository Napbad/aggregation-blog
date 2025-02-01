import type {Executor} from '../index.ts';
import type {
    ArticleCreateInput, 
    ArticleCreateOutput, 
    ArticleQueryOutput, 
    ArticleSpecification, 
    Page
} from '../model/static';

export class ArticleController {
    
    constructor(private executor: Executor) {}
    
    readonly add: (options: ArticleControllerOptions['add']) => Promise<
        ArticleCreateOutput
    > = async(options) => {
        let _uri = '/add';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<ArticleCreateOutput>;
    }
    
    readonly getArticle: (options: ArticleControllerOptions['getArticle']) => Promise<
        Page<ArticleQueryOutput>
    > = async(options) => {
        let _uri = '/query';
        return (await this.executor({uri: _uri, method: 'POST', body: options.body})) as Promise<Page<ArticleQueryOutput>>;
    }
}

export type ArticleControllerOptions = {
    'add': {
        readonly body: ArticleCreateInput
    }, 
    'getArticle': {
        readonly body: ArticleSpecification
    }
}
