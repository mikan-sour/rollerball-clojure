import { error } from "console"

interface IBody extends Object {}

interface IRequestArgs<T> {
    method:T,
    url:string,
    headers?:Headers
    body: IBody
}

export default function Client() {
    return {
        get,
        post:() => undefined,
        patch: () => undefined,
        delete: () => undefined
    }
}

async function get({url,headers,method}:IRequestArgs<"GET">){
    try {
        const req = await fetch(url)
        if(!req.ok) {
            throw error(req.statusText)
        }
        return await req.json();
    } catch (error) {
        console.error(error)
    }
}