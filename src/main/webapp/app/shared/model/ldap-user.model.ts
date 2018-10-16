export interface ILdapUser {
    id?: string;
    userID?: string;
    name?: string;
    userChannelConfigurationId?: string;
}

export class LdapUser implements ILdapUser {
    constructor(public id?: string, public userID?: string, public name?: string, public userChannelConfigurationId?: string) {}
}
