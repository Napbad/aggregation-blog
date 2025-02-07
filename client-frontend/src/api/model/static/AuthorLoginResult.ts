import {AuthorLoginOutput} from "@/api/model/static/AuthorLoginOutput.ts";

export interface AuthorLoginResult {
    readonly authorLoginOutput: AuthorLoginOutput
    readonly msg: string
}
