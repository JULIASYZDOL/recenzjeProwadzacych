'use client'

import { useRouter, useSearchParams } from 'next/navigation';
import { useState } from 'react';

export default function Komentarz() {

  const router = useRouter();
  const [pseudonimQuery, setPseudonimQuery] = useState('');
  const [trescQuery, setTrescQuery] = useState('');
  const params = useSearchParams();

  const idProwadzacego = params.get('id') || ''; 

  const handlePublish = async () => {
    try {
      const apiPath = 'http://localhost:8080/komentarze';

      const commentData = {
        id: Number,
        tytul: "Komentarz",
        tresc: trescQuery,
        pseudonim: pseudonimQuery,
        idProwadzacego: idProwadzacego
      };

      const response = await fetch(apiPath, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(commentData),
      });

      if (!response.ok) {
        throw new Error('Failed to publish comment');
      }
      router.back();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="container">
        <h1>Dodajesz komentarz</h1>
        <input
        type="text"
        placeholder="Twój pseudonim"
        value={pseudonimQuery}
        onChange={(e) => setPseudonimQuery(e.target.value)}
        />
        <input
        type="text"
        placeholder="Treść komentarza"
        value={trescQuery}
        onChange={(e) => setTrescQuery(e.target.value)}
        />
        <div className="button-container">
          <button type="button" onClick={handlePublish}>Opublikuj</button>
          <button type="button" onClick={() => router.back()}>Anuluj</button>
        </div>
    </div>
  )
}