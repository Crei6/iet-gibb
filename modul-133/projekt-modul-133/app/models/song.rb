class Song < ApplicationRecord
    belongs_to :album
    validates_presence_of :title, message: "darf nicht leer sein"
    validates_numericality_of :duration, :message => "darf nur eine Zahl sein"
end
