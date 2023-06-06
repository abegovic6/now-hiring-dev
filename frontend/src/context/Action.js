// Context/actions.js


import {get, put} from "../methods";
import {token} from "./Reducer";

export async function loginUser(dispatch, loginPayload) {
    let data = undefined;
    try {
        dispatch({ type: 'REQUEST_LOGIN' });
        let result = await put('user-service/user/auth/authenticate', loginPayload)

        data = result;
        console.info(result)
        if (!result.errors) {
            let userResponse = await get('user-service/user/email/' + loginPayload.email,
                undefined, result.access_token)
            if (!userResponse.errors) {
                dispatch({ type: 'LOGIN_SUCCESS', payload: {token: result.access_token} });
                localStorage.setItem('currentUser', JSON.stringify( {
                    user: userResponse,
                    token: result.access_token
                }));
            }

        } else {
            dispatch({ type: 'LOGIN_ERROR', error: result.errors[0] });
        }

    } catch (error) {
        dispatch({ type: 'LOGIN_ERROR', error: error });
    }

    return data;
}

export async function logout(dispatch) {
    dispatch({ type: 'LOGOUT' });
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
}

export function getUser(dispatch) {

}