import type {Executor} from './index.ts';
import {ArticleController, UserAccountController} from './services';

export class Api {
    
    readonly articleController: ArticleController
    
    readonly userAccountController: UserAccountController
    
    constructor(executor: Executor) {
        this.articleController = new ArticleController(executor);
        this.userAccountController = new UserAccountController(executor);
    }
}