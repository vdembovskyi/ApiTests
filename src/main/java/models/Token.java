package models;

public class Token {

    public Token(String code, String grant_type, String client_secret, String redirect_uri, String client_id) {
        this.code = code;
        this.grant_type = grant_type;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
        this.client_id = client_id;
    }

    private String code;

    private String grant_type;

    private String client_secret;

    private String redirect_uri;

    private String client_id;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getGrant_type ()
    {
        return grant_type;
    }

    public void setGrant_type (String grant_type)
    {
        this.grant_type = grant_type;
    }

    public String getClient_secret ()
    {
        return client_secret;
    }

    public void setClient_secret (String client_secret)
    {
        this.client_secret = client_secret;
    }

    public String getRedirect_uri ()
    {
        return redirect_uri;
    }

    public void setRedirect_uri (String redirect_uri)
    {
        this.redirect_uri = redirect_uri;
    }

    public String getClient_id ()
    {
        return client_id;
    }

    public void setClient_id (String client_id)
    {
        this.client_id = client_id;
    }

    @Override
    public String toString()
    {
        return "File [code = "+code+", grant_type = "+grant_type+", client_secret = "+client_secret+", redirect_uri = "+redirect_uri+", client_id = "+client_id+"]";
    }
}

