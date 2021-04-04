class AlbumsController < ApplicationController

    def index
        @albums = Album.all
        @albums.each do |a|
            a.duration = a.songs.sum(:duration)
            a.no_songs = a.songs.count
            a.save
        end
    end

    def show
        @album = Album.find(params[:id])
        i = 1
        @album.songs.each do |s|
            s.position = i
            s.save
            i += 1
        end
    end

    def new
        @title = "Album erfassen"
        @album = Album.new 
        render action: "edit"
    end

    def create 
        @album = Album.new(album_params) 
        if @album.save 
            redirect_to action: "index" 
        else 
            render action: "new" 
        end 
    end 
    
    def album_params 
        params.require(:album).permit(:band, :title, :year, :no_songs, :duration) 
    end

    def edit
        @title = "Album bearbeiten"
        @album = Album.find(params[:id]) 
    end

    def update
        @album = Album.find(params[:id]) 
        if @album.update(album_params) 
            redirect_to action: "index" 
        else 
            render action: "edit" 
        end
    end

    def destroy
        Album.find(params[:id]).destroy 
        redirect_to action: "index"
    end

end
