class BooksController < ApplicationController

    def index
        @books = Book.all
    end

    def show
        @book = Book.find(params[:id])
    end

    def new
        @title = "Buch erfassen"
        @action = "create"
        @book = Book.new 
        @subjects = Subject.all
        render action: "edit"
    end

    def create 
        @book = Book.new(book_params) 
        if @book.save 
            redirect_to action: "index" 
        else 
            @subjects = Subject.all 
            render action: "new" 
        end 
    end 
    
    def book_params 
        params.require(:book).permit(:title, :price, :subject_id, :description) 
    end

    def edit
        @title = "Buch bearbeiten"
        @action = "update"
        @book = Book.find(params[:id]) 
        @subjects = Subject.all
    end

    def update
        @book = Book.find(params[:id]) 
        if @book.update(book_params) 
            redirect_to action: "index" 
        else 
            @subjects = Subject.all 
            render action: "edit" 
        end
    end

    def destroy
        Book.find(params[:id]).destroy 
        redirect_to action: "index"
    end

end
