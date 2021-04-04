class Book < ApplicationRecord
    belongs_to :subject
    validates_presence_of :title, message: "darf nicht leer sein"
    validates_numericality_of :price, :message => "darf nur eine Zahl sein"
end
