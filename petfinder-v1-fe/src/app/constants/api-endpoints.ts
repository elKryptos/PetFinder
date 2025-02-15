export const API_BASE = 'http://localhost:8080/'

export const API_ENDPOINTS = {

    AUTH: {
        SIGNIN: `${API_BASE}/auth/signin`,
        LOGIN: `${API_BASE}/auth/login`,
        LOGOUT: `${API_BASE}/auth/logout`,
        CONTROL: `${API_BASE}/auth/control`
    },

    USER: {
        ALL_USERS: `${API_BASE}/user`,
        CREATE_ANIMAL: `${API_BASE}/user`,
        UPDATE:(userId: number) => `${API_BASE}/user/update/${userId}`,
        DELETE: (userId: number) => `${API_BASE}/user/delete/${userId}`,
        FIND_BY_ID: (userId: number) => `${API_BASE}/user/id/${userId}`,
        FIND_BY_EMAIL: (email: string) => `${API_BASE}/user/email/${email}`
    },

    ANIMAL: {
        ALL_ANIMAL: `${API_BASE}/animal`,
        CREATE_ANIMAL: `${API_BASE}/animal/`,
        UPDATE: (animalId: number) => `${API_BASE}/animal/update/${animalId}`,
        DELETE: (animalId: number) => `${API_BASE}/animal/delete/${animalId}`,
        FIND_BY_ID: (animalId: number) => `${API_BASE}/animal/id/${animalId}`
    }
}