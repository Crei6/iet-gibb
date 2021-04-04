class SongsController < ApplicationController

    def index
        @songs = Song.all
    end

    def show
        @song = Song.find(params[:id])
    end

    def new
        @title = "Song erfassen"
        @song = Song.new 
        @albums = Album.select("(band || ': ' || title) as title")
        render action: "edit"
    end

    def create 
        @song = Song.new(song_params) 
        if @song.save 
            redirect_to action: "index" 
        else 
            render action: "new" 
        end 
    end 
    
    def song_params 
        params.require(:song).permit(:album_id, :position, :title, :duration) 
    end

    def edit
        @title = "Song bearbeiten"
        @song = Song.find(params[:id]) 
        @albums = Album.all
    end

    def update
        @song = Song.find(params[:id]) 
        if @song.update(song_params) 
            redirect_to action: "index" 
        else 
            render action: "edit" 
        end
    end

    def destroy
        Song.find(params[:id]).destroy 
        redirect_to action: "index"
    end

end
