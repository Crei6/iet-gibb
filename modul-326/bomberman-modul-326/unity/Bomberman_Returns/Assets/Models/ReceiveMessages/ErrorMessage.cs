using System.Collections;
using System.Collections.Generic;

public class ErrorMessage : Message
{

    private string errorMessage;

    public ErrorMessage(string errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public string GetErrorMessage()
    {
        return errorMessage;
    }
}
