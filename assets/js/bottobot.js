//basic format of a bot


//bot object
var bot = {
    //bot name
    name: "",
    //bot description
    description: "",
    //bot avatar
    avatar: "",
    //bot commands
    commands: [],
    //bot events
    events: [],
    //bot settings
    settings: {
        //bot prefix
        prefix: "",
        //bot owner
        owner: "",
        //bot token
        token: "",
        //bot language
        language: "",
        //bot version
        version: "",
        //bot status
        status: "",
        //bot uptime
        uptime: "",
        
    },
    //bot functions
    functions: {
        //bot send message
        sendMessage: function(message, channel) {
            //send message
            bot.functions.sendMessage(message, channel);
        },
        //bot send embed
        sendEmbed: function(embed, channel) {
            //send embed
            bot.functions.sendEmbed(embed, channel);
        },
        //bot send file
        sendFile: function(file, channel) {
            //send file
            bot.functions.sendFile(file, channel);
        },
        //bot send typing
        sendTyping: function(channel) {
            //send typing
            bot.functions.sendTyping(channel);
        },
        //bot send typing
        sendTypingStop: function(channel) {
            //send typing
            bot.functions.sendTypingStop(channel);
        }
    }
}
