import type {Executor} from './';
import {UserAccountController} from './services/';

export class Api {
    
    readonly userAccountController: UserAccountController
    
    constructor(executor: Executor) {
        this.userAccountController = new UserAccountController(executor);
    }
}